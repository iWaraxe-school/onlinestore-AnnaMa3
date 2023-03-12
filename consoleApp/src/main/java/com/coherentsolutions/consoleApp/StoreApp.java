package com.coherentsolutions.consoleApp;

import com.coherentsolutions.store.Store;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class StoreApp {
    public static void main(String[] args) throws XMLStreamException, IOException, SQLException, ClassNotFoundException {
        Store onlineStore = Store.getStore();
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        Connection connection = dataBaseHandler.getDbConnection();
        dataBaseHandler.dropDatabase();
        dataBaseHandler.createCategoryTable();
        dataBaseHandler.createProductTable();
        dataBaseHandler.fillStore(onlineStore);



//        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
//        randomStorePopulator.fillStore();
//        onlineStore.printAll();
        Runnable clearOrder = new ClearOrder();
        new Thread(clearOrder).start();



        System.out.println("\n --Enter-- \n" +
                "sort \t products from store according config \n" +
                "top \t print top 5 products of whole store sorted via price desc \n" +
                "order \t print ordered products of whole store \n" +
                "exit app quit\n");

        while (true) {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            String request = is.readLine();
            new SortChain().execute(request);


        }
    }
}
