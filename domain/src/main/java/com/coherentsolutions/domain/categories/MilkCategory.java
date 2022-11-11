package com.coherentsolutions.domain.categories;
import com.coherentsolutions.domain.Categories;
import com.coherentsolutions.domain.Category;

public class MilkCategory extends Category {
    public MilkCategory() {
        super(Categories.MILK.name());
    }

    @Override
    public String getName() {
        return Categories.MILK.name();
    }
}
