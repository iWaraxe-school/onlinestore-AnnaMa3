package com.coherentsolutions.domain;
public class Product {
    private String name;
    private double rate;
    private double price;

    public Product(String name, int rate, int price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString(){
        return "Name is " + name + ", with rate: " + rate + " and price: " + price;
    }


}
