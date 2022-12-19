package com.coherentsolutions.store.StoreHelper.Comparator;

import com.coherentsolutions.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Comparator {

    static List<java.util.Comparator<Product>> comparatorList = new ArrayList<java.util.Comparator<Product>>();

    public static java.util.Comparator<? super Product> compare(Map<String, String> linkedHashMap){
        Set<String> keys = linkedHashMap.keySet();

        java.util.Comparator<Product> nameComparator = java.util.Comparator.comparing(Product::getName, String ::compareToIgnoreCase);
        java.util.Comparator<Product> priceComparator = java.util.Comparator.comparing(Product::getPrice, Double::compareTo);
        java.util.Comparator<Product> rateComparator = java.util.Comparator.comparing(Product::getRate, Double::compareTo);

        for (String key : keys) {
            if (linkedHashMap.get(key).equals("desc")){
                System.out.println(key);
                System.out.println(linkedHashMap.get(key));
                if (key.equals("name")){
                    nameComparator = nameComparator.reversed();
                } else if (key.equals("price")){
                    priceComparator = priceComparator.reversed();
                } else {
                    rateComparator = rateComparator.reversed();
                }

            } else {
                System.out.println(key);
                System.out.println(linkedHashMap.get(key));
            }
        }


        for (Map.Entry<String, String> entry : linkedHashMap.entrySet())
            if (entry.getKey().equals("name")){
            comparatorList.add(nameComparator);
        } else if (entry.getKey().equals("price")){
                comparatorList.add(priceComparator);
        } else {
                comparatorList.add(rateComparator);
            }


        java.util.Comparator<Product> generalComparator = (java.util.Comparator<Product>) comparatorList.get(0);
        for (int i=1; i<comparatorList.size(); i++){
            generalComparator = generalComparator.thenComparing(comparatorList.get(i));

        }

        return generalComparator;
    }

}
