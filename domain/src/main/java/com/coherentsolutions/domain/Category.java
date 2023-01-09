package com.coherentsolutions.domain;

import java.util.*;

public abstract class Category {
    private String name;
    private List<Product> productList;

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
}
