package com.coherentsolutions.consoleApp;

import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper.RandomStorePopulator;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class StoreApp {
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStore();
        onlineStore.printAll();



    }
}
