package com.coherentsolutions.consoleApp;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper.Comparator.Comparator;
import com.coherentsolutions.store.StoreHelper.RandomStorePopulator;
import com.coherentsolutions.store.StoreHelper.XMLParser.XmlParser;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class StoreApp {
    public static void main(String[] args) throws XMLStreamException, IOException {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStore();
        onlineStore.printAll();

        Map<String,String> map = XmlParser.parseToMap();
        List<Product> allStoreProducts = onlineStore.getAllProductList();

        System.out.println("\n --Enter-- \n" +
                "sort \t products from store according config \n" +
                "top \t print top 5 products of whole store sorted via price desc \n" +
                "exit app quit\n");

        while (true) {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            String a = is.readLine();

            if (a.equals("sort")) {
                allStoreProducts.sort(Comparator.compare(map));
                for (Product product : allStoreProducts) {
                    System.out.println(product);
                }
            } else if (a.equals("top")) {
                allStoreProducts.sort(java.util.Comparator.comparing(Product::getPrice, Double::compareTo).reversed());
                for (int i = 0; i < 5; i++) {
                    System.out.println(allStoreProducts.get(i));
                }
            } else if (a.equals("quit")) {
                System.exit(0);
            }
        }


    }
}
