package com.coherentsolutions.store;
import com.coherentsolutions.domain.Category;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Category> categoryList= new ArrayList<>();

    public void addCategory (Category category) {
       categoryList.add(category);
    }

    public void printAll() {
        for (Category category : categoryList) {
            category.printProducts();
        }
    }
}
