package com.coherentsolutions.domain.categories;
import com.coherentsolutions.domain.Categories;
import com.coherentsolutions.domain.Category;
import com.github.javafaker.Faker;

public class MilkCategory extends Category implements CategoryType {

    private static Faker faker = new Faker();
    public MilkCategory() {
        super(Categories.MILK.name());
    }

    @Override
    public String getName() {
        return Categories.MILK.name();
    }

    @Override
    public String generateProductName(String categoryName) {
        return faker.book().title();
    }
}
