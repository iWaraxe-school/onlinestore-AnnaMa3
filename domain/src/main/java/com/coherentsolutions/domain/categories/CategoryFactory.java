package com.coherentsolutions.domain.categories;

import java.util.Objects;

public class CategoryFactory {
    private static final String BIKE_CATEGORY = "bike";
    private static final String MILK_CATEGORY = "milk";
    private static final String PHONE_CATEGORY = "phone";


    public CategoryType getCategory(String name) {
        if (Objects.equals(name, BIKE_CATEGORY)) {
            return new BikeCategory();
        } else if (Objects.equals(name, MILK_CATEGORY)) {
            return new MilkCategory();
            } else if (Objects.equals(name,PHONE_CATEGORY)) {
            return new PhoneCategory();
        }
        return null;
    }
}

