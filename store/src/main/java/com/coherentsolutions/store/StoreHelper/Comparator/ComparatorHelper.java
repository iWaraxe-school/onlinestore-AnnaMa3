package com.coherentsolutions.store.StoreHelper.Comparator;

import com.coherentsolutions.domain.Product;

import java.util.*;

public class ComparatorHelper {

    static List<Comparator<Product>> comparatorList = new ArrayList<>();

    public static Comparator<? super Product> compare(Map<String, String> linkedHashMap){
        Set<String> keys = linkedHashMap.keySet();

        Comparator<Product> nameComparator = Comparator.comparing(Product::getName, String ::compareToIgnoreCase);
        Comparator<Product> priceComparator = Comparator.comparing(Product::getPrice, Double::compareTo);
        Comparator<Product> rateComparator = Comparator.comparing(Product::getRate, Double::compareTo);

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


        Comparator<Product> generalComparator = (Comparator<Product>) comparatorList.get(0);
        for (int i=1; i<comparatorList.size(); i++){
            generalComparator = generalComparator.thenComparing(comparatorList.get(i));

        }

        return generalComparator;
    }

}
