package com.coherentsolutions.consoleApp;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper.RandomStorePopulator;

public class StoreApp {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillStore();
        onlineStore.printAll();

    }
}
