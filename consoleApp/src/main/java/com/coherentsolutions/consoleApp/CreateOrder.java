package com.coherentsolutions.consoleApp;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class CreateOrder extends Thread{
    private final Store store;

    {
        try {
            store = Store.getStore();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private  final PurchaseProducts purchaseProducts;

    {
        try {
            purchaseProducts = PurchaseProducts.getPurchaseProducts();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
        Product purchaseProduct = store.getRandomProduct();
        System.out.println("Ordered product: " + purchaseProduct);
        purchaseProducts.addPurchasedProduct(purchaseProduct);
        purchaseProducts.printPurchasedProducts();
//        purchaseProducts.clearPurchasedProducts();

    }
}
