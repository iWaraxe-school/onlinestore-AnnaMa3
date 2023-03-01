package com.coherentsolutions.consoleApp;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class ClearOrder implements Runnable{
    private final PurchaseProducts purchaseProducts;

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
        while (true){
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            System.out.println("-------------------------------");
            System.out.println("Thread: " + Thread.currentThread().getName());
            purchaseProducts.clearPurchasedProducts();
            System.out.println("List of Products was cleared");
            System.out.println("-------------------------------");
            purchaseProducts.printPurchasedProducts();
        }

    }
}
