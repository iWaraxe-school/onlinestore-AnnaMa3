package com.coherentsolutions.consoleApp;

import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper.RandomStorePopulator;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreApp {
    public static void main(String[] args) throws XMLStreamException, IOException {
        Store onlineStore = Store.getStore();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStore();
        onlineStore.printAll();


        System.out.println("\n --Enter-- \n" +
                "sort \t products from store according config \n" +
                "top \t print top 5 products of whole store sorted via price desc \n" +
                "exit app quit\n");

        while (true) {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            String request = is.readLine();
            new SortChain().execute(request);


        }
    }
}
