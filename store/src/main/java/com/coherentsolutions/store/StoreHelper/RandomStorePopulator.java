package com.coherentsolutions.store.StoreHelper;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;
import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private Store store;
    private Faker faker = new Faker();

    public RandomStorePopulator (Store store) {
        this.store = store;
    }

    private String generateProductName(){
        return String.valueOf(faker.name());
    }

    private double generateProductRate(){
        return faker.number().randomDouble(1,0,5);
    }

    private double generateProductPrice(){
        return faker.number().randomDouble(1,0,1000);
    }

    public Product generateProduct(Product product){
        return new Product(
                generateProductName(),
                generateProductRate(),
                generateProductPrice());
    }





}
