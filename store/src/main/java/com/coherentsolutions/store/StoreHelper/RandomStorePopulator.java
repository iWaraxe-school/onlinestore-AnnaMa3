package com.coherentsolutions.store.StoreHelper;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
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

    private String generateProductName(String categoryName){
        switch (categoryName) {
            case "BIKE":
                return faker.hipster().word();
            case "MILK":
                return faker.food().ingredient();
            case "PHONE":
                return faker.app().name();
            default:
                return null;
        }
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
