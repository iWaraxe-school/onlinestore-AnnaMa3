package com.coherentsolutions.consoleApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StoreDAO {
    DataBaseHandler connectionDb;

    public StoreDAO(DataBaseHandler connectionDb){
        this.connectionDb = connectionDb;}

    public String getAllProducts(){
        try (Connection connection = connectionDb.getDbConnection();
             Statement statement = connection.createStatement()){
             StringBuilder sb = new StringBuilder();

             ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");
             while (resultSet.next()){
                 String product = new StringBuilder().append("Name: ").append(resultSet.getString("Name")).append(", Rate: ").append(resultSet.getString("Rate")).append(", Price: ").append(resultSet.getString("Price")).toString();
                 sb.append(product);
                 sb.append("<br>");

             }
             return sb.toString();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


}
