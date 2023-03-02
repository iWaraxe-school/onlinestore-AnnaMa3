package com.coherentsolutions.consoleApp;

import com.coherentsolutions.domain.Product;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseProducts {
    private final List<Product> purchaseProductList = new ArrayList<>();
    private static PurchaseProducts purchaseProducts;

    public static PurchaseProducts getPurchaseProducts() throws XMLStreamException, FileNotFoundException {
        if (purchaseProducts == null){
            purchaseProducts = new PurchaseProducts();
        }
        return purchaseProducts;
    }

    public synchronized void addPurchasedProduct(Product product){
        purchaseProductList.add(product);
    }

    public synchronized void clearPurchasedProducts(){
        purchaseProductList.clear();
    }
    public void printPurchasedProducts(){
        System.out.println(" ");
        System.out.println("--------List of Products-------");
        System.out.println(" ");

        for (Product purchaseProduct : purchaseProductList) {
            System.out.println(purchaseProduct);
        }

    }


}
