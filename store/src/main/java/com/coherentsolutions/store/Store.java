package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import com.github.javafaker.Faker;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private static Store store;
    private Faker faker = new Faker();

    private List<Category> categoryList= new ArrayList<>();

    private Store() throws XMLStreamException, FileNotFoundException {
    }

    public static Store getStore() throws XMLStreamException, FileNotFoundException {
        if (store == null){
            store = new Store();
        }
        return store;
    }

    public void addCategory (Category category) {
       categoryList.add(category);
    }

    public void printAll() {
        for (Category category : categoryList) {
            category.printProducts();
        }
    }

    public List<Product> getAllProductList() {
        List<Product> allProducts = new ArrayList<Product>();
        for (Category category : categoryList) {
            List<Product> categoryProducts = category.getProducts();
            allProducts.addAll(categoryProducts);
        }
        return allProducts;
    }

    public Product getRandomProduct() {
        Product randomProduct = null;
        for (Category category : categoryList) {
            randomProduct = category.getProduct();
        }
        return randomProduct;
    }

}
