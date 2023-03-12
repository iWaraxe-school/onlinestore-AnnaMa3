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
    private static Statement STATEMENT_ENCLOSED = null;
    private static Statement STATEMENT = null;
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



}
