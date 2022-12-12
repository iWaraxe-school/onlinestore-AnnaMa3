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

    public List<Product> compare(Map<String, String> linkedHashMap){
        Set<String> keys = linkedHashMap.keySet();

        Comparator<Product> nameComparator = Comparator.comparing(Product::getName, String ::compareToIgnoreCase);
        Comparator<Product> priceComparator = Comparator.comparing(Product::getPrice, Double::compareTo);
        Comparator<Product> rateComparator = Comparator.comparing(Product::getRate, Double::compareTo);

        for (String key : keys) {
            if (linkedHashMap.get(key).equals("desc")){
                System.out.println(key);
                System.out.println(linkedHashMap.get(key));
                if (key.equals("name")){
                    nameComparator = nameComparator.reversed();
                } else if (key.equals("price")){
                    priceComparator = priceComparator.reversed();
                } else {
                    rateComparator = rateComparator.reversed();
                }

            } else {
                System.out.println(key);
                System.out.println(linkedHashMap.get(key));
            }
            }

        Comparator<Product> generalComparator = nameComparator.thenComparing(priceComparator).thenComparing(rateComparator);
        productList.sort(generalComparator);
        return productList;

    }

    public void printProducts() {
        System.out.println("");
        System.out.println("--" + name + "--");

        for (Product product : productList) {
            System.out.println(product);
        }

    }
}
