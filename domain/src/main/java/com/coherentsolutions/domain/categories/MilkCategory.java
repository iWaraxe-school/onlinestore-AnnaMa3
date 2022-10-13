package com.coherentsolutions.domain.categories;
import com.coherentsolutions.domain.Category;

public class MilkCategory extends Category {
    public MilkCategory(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return "Milk";
    }
}
