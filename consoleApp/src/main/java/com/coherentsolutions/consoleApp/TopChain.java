package com.coherentsolutions.consoleApp;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;

public class TopChain extends Command{
    public TopChain() throws XMLStreamException, FileNotFoundException {
        super(new CreateOrderChain());
    }

    @Override
    public void execute (String request) throws XMLStreamException, FileNotFoundException {
        if (request.equals("top")) {
            List<Product> allProducts = Store.getStore().getAllProductList();
            allProducts.sort(Comparator.comparing(Product::getPrice, Double::compareTo).reversed());
            for (int i = 0; i < 5; i++) {
                System.out.println(allProducts.get(i));
            }
        }
        else if (getNext() != null){
            getNext().execute(request);
        }
    }

}