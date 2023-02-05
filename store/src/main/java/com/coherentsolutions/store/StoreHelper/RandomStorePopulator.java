package com.coherentsolutions.store.StoreHelper;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;
import com.github.javafaker.Faker;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;


public class RandomStorePopulator {

    private Store store;
    private Faker faker = new Faker();

    public RandomStorePopulator (Store store) {
        this.store = store;
    }

    private String generateProductName(String categoryName){
        switch (categoryName) {
            case "BIKE":
                return faker.color().name();
            case "MILK":
                return faker.food().ingredient();
            case "PHONE":
                return faker.animal().name();
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
        CategoryFactory categoryFactory = new CategoryFactory();

        Reflections reflections = new Reflections("com.coherentsolutions.domain.categories");
        Set<Class<? extends Category>> subCategoryTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subCategoryType : subCategoryTypes) {
            String simpleName = subCategoryType.getSimpleName();
            Category categoryToAdd = categoryFactory.createCategory(simpleName);
            store.addCategory(categoryToAdd);
            categorySet.add(categoryToAdd);
        }

        for (Category category : categorySet){
            for (int i = 0; i < faker.number().randomDigitNotZero(); i++) {
                category.addProduct(generateProduct(category.getName()));
            }
        }
    }


}
