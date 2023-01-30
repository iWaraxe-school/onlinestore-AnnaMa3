package com.coherentsolutions.domain.categories;
import com.coherentsolutions.domain.Categories;
import com.coherentsolutions.domain.Category;
import com.github.javafaker.Faker;

public class BikeCategory extends Category implements CategoryType {
    private Faker faker = new Faker();

    public BikeCategory() {
        super(Categories.BIKE.name());
    }

    @Override
    public String getName() {
        return Categories.BIKE.name();
    }

    @Override
    public String generateProductName(String categoryName) {
        return faker.color().name();
    }

}
