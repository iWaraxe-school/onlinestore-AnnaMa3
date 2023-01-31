package com.coherentsolutions.domain.categories;

public interface CategoryType {

    String name = new String();
    default String generateProductName(String categoryName){
        return name;
    }
}
