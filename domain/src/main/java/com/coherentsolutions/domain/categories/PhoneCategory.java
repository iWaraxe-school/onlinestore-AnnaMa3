package com.coherentsolutions.domain.categories;
import com.coherentsolutions.domain.Categories;
import com.coherentsolutions.domain.Category;

public class PhoneCategory extends Category {
    public PhoneCategory(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return Categories.PHONE.name();
    }
}
