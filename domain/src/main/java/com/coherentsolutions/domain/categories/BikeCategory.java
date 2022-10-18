package com.coherentsolutions.domain.categories;
import com.coherentsolutions.domain.Categories;
import com.coherentsolutions.domain.Category;

public class BikeCategory extends Category {

    public BikeCategory(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return Categories.BIKE.name();
    }
}
