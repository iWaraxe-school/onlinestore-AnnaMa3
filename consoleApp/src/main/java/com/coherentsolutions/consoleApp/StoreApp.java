package com.coherentsolutions.consoleApp;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper.Comparator.Comparator;
import com.coherentsolutions.store.StoreHelper.RandomStorePopulator;
import com.coherentsolutions.store.StoreHelper.XMLParser.XmlParser;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class StoreApp {
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStore();
        onlineStore.printAll();

        Map<String,String> map = XmlParser.parseToMap();
        List<Product> allStoreProducts = onlineStore.getAllProductList();
        allStoreProducts.sort(Comparator.compare(map));

        for (Product product : allStoreProducts) {
            System.out.println(product);
        }



    }
}
