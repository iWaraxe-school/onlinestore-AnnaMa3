package com.coherentsolutions.domain.categories;
import com.coherentsolutions.domain.Categories;
import com.coherentsolutions.domain.Category;
import com.github.javafaker.Faker;

public class PhoneCategory extends Category implements CategoryType{
    private Faker faker = new Faker();
    public PhoneCategory() {
        super(Categories.PHONE.name());
    }

    @Override
    public String getName() {
        return Categories.PHONE.name();
    }
    @Override
    public String generateProductName(String categoryName) {
        return faker.animal().name();
    }
}
