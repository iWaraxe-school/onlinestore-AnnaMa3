package com.coherentsolutions.consoleApp;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper.Comparator.ComparatorHelper;
import com.coherentsolutions.store.StoreHelper.RandomStorePopulator;
import com.coherentsolutions.store.StoreHelper.XMLParser.XmlParser;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StoreApp {
    public static void main(String[] args) throws XMLStreamException, IOException {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStore();
        onlineStore.printAll();

        Map<String, String> map = XmlParser.parseToMap();

        System.out.println("\n --Enter-- \n" +
                "sort \t products from store according config \n" +
                "top \t print top 5 products of whole store sorted via price desc \n" +
                "exit app quit\n");

        while (true) {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            String a = is.readLine();

            List<Product> allStoreProducts = onlineStore.getAllProductList();

            switch (a) {
                case "sort": {
                    allStoreProducts.sort(ComparatorHelper.compare(map));
                    for (Product product : allStoreProducts) {
                        System.out.println(product);
                    }
                };
                break;

                case "top": {
                    allStoreProducts.sort(Comparator.comparing(Product::getPrice, Double::compareTo).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(allStoreProducts.get(i));
                    }
                };
                break;

                case "quit": {
                    System.exit(0);
                };
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + a);
            }

        }
    }
}
