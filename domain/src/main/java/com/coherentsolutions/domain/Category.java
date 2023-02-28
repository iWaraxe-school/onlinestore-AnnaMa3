package com.coherentsolutions.domain;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    private String name;
    private List<Product> productList;
    private Faker faker = new Faker();

    public Category(String name) {
        this.name = name;
        productList = new ArrayList<>();
    }

    public String getName() {
        return name;
    };

    public void addProduct (Product product) {
        productList.add(product);
    }

    public void printProducts() {
        System.out.println("");
        System.out.println("--" + name + "--");

        for (Product product : productList) {
            System.out.println(product);
        }

    }


    public List<Product> getProducts() {
        return productList;
    }

    public Product getProduct() {
        Product randomProduct = productList.get(faker.number().numberBetween(1, productList.size()));
        return randomProduct;
    }
}
