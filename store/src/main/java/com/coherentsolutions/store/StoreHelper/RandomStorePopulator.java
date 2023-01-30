package com.coherentsolutions.store.StoreHelper;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import com.coherentsolutions.domain.categories.*;
import com.coherentsolutions.store.Store;
import com.github.javafaker.Faker;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class RandomStorePopulator {

    private Store store;
    private Faker faker = new Faker();

    public RandomStorePopulator (Store store) {
        this.store = store;
    }

    CategoryFactory categoryFactory = new CategoryFactory();
    CategoryType bike = categoryFactory.getCategory("bike");
    CategoryType milk = categoryFactory.getCategory("milk");
    CategoryType phone = categoryFactory.getCategory("phone");
    List<CategoryType> categoryTypeList = new ArrayList<>();


    public String generateProductName(String categoryName) {
        categoryTypeList.add(milk);
        categoryTypeList.add(bike);
        categoryTypeList.add(phone);
        String productName = new String();
        for (CategoryType category : categoryTypeList) {
              if (category.toString().toLowerCase().contains(categoryName.toLowerCase())) {
                  productName = category.generateProductName(categoryName);
              }
        }
        return productName;
    }

    private double generateProductRate(){
        return faker.number().randomDouble(1,0,5);
    }

    private double generateProductPrice(){
        return faker.number().randomDouble(1,0,1000);

    }

    public Product generateProduct(String categoryName){
        return new Product(
                generateProductName(categoryName),
                generateProductRate(),
                generateProductPrice());
    }

    public void fillStore() {
        Set<Category> categorySet = new HashSet<>();

        Reflections reflections = new Reflections("com.coherentsolutions.domain.categories");
        Set<Class<? extends Category>> subCategoryTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subCategoryType : subCategoryTypes) {
            try {
                Category categoryToAdd = subCategoryType.getConstructor().newInstance();
                store.addCategory(categoryToAdd);
                categorySet.add(categoryToAdd);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        for (Category category : categorySet){
            for (int i = 0; i < faker.number().randomDigitNotZero(); i++) {
                category.addProduct(generateProduct(category.getName()));
            }
        }
    }


}
