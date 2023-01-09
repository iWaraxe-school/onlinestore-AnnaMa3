package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Category> categoryList= new ArrayList<>();

    public Store() throws XMLStreamException, FileNotFoundException {
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
}
