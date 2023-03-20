package com.coherentsolutions.consoleApp;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper.CategoryFactory;
import com.coherentsolutions.store.StoreHelper.RandomStorePopulator;
import com.github.javafaker.Faker;
import org.reflections.Reflections;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DataBaseHandler extends Config {
    static ResultSet RESULT = null;
    static Statement STATEMENT_ENCLOSED = null;
    static Statement STATEMENT = null;
    Connection dbConnection;
    private Faker faker = new Faker();
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
       String connectionString ="jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
       dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        System.out.println("True");
        STATEMENT = dbConnection.createStatement();
        STATEMENT_ENCLOSED = dbConnection.createStatement();

       return dbConnection;
    }

    public void dropDatabase() throws SQLException {
        String sql1 = "DROP TABLE IF EXISTS Categories, Products";
        STATEMENT.executeUpdate(sql1);
    }

    public void createCategoryTable() throws SQLException {
        String sql2 = "CREATE TABLE Categories (" + "ID int PRIMARY KEY AUTO_INCREMENT, " + " Name nvarchar(100) NOT NULL);";
        STATEMENT.executeUpdate(sql2);
    }

    public void createProductTable() throws SQLException {
        String sql3 = "CREATE TABLE Products (" + "ID int PRIMARY KEY AUTO_INCREMENT, "
                + " Name nvarchar(100) NOT NULL, "
                + "Rate float(53) NOT NULL, "
                + "Price float(53) NOT NULL, "
                + "Category_ID int NOT NULL, "
                + "CONSTRAINT fk_categories FOREIGN KEY (Category_ID) REFERENCES Categories(ID));";
        STATEMENT.executeUpdate(sql3);
    }

    private static Set<Category> createCategorySet() {
        Set<Category> categorySet = new HashSet<>();
        CategoryFactory categoryFactory = new CategoryFactory();

        Reflections reflections = new Reflections("com.coherentsolutions.domain.categories");
        Set<Class<? extends Category>> subCategoryTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subCategoryType : subCategoryTypes) {
            String simpleName = subCategoryType.getSimpleName();
            Category categoryToAdd = categoryFactory.createCategory(simpleName);
            categorySet.add(categoryToAdd);
        }

        return categorySet;
    }

    public void fillStore(Store store) throws SQLException, XMLStreamException, FileNotFoundException {
        Set<Category> categorySet = createCategorySet();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);
        int j = 1;

        for (Category category : categorySet) {
            PreparedStatement insertCategories = dbConnection.prepareStatement("INSERT INTO Categories(Name) VALUES(?)");
            insertCategories.setString(1, category.getName());
            insertCategories.execute();


            for (int i = 0; i < faker.number().randomDigitNotZero(); i++) {
                PreparedStatement insertProduct = dbConnection.prepareStatement("INSERT INTO Products(Name, Rate, Price, Category_ID) VALUES(?, ?, ?, ?)");
                insertProduct.setString(1, randomStorePopulator.generateProduct(category.getName()).getName());
                insertProduct.setDouble(2, randomStorePopulator.generateProduct(category.getName()).getRate());
                insertProduct.setDouble(3, randomStorePopulator.generateProduct(category.getName()).getPrice());
                insertProduct.setInt(4, j);
                insertProduct.execute();
            }
            j++;
        }
    }

    public void printStore() throws SQLException, XMLStreamException, FileNotFoundException {
        ResultSet rs1 = STATEMENT.executeQuery("SELECT * FROM Categories");

        while(rs1.next()) {
            System.out.println("-----------------------------------------------------");
            System.out.println(rs1.getString("Name"));
            System.out.println("-----------------------------------------------------");

            int categoryId1 = rs1.getInt("ID");
            ResultSet rs2 = STATEMENT_ENCLOSED.executeQuery("SELECT * FROM Products");

            while (rs2.next()){
                int categoryId2 = rs2.getInt("Category_ID");
                if (categoryId1 == categoryId2){
                    System.out.println("Name: " + rs2.getString("Name") + ", Rate: " + rs2.getString("Rate") + ", Price: " + rs2.getString("Price"));

                }

            }

        }


    }



}
