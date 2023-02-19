package com.coherentsolutions.consoleApp;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper.Comparator.ComparatorHelper;
import com.coherentsolutions.store.StoreHelper.XMLParser.XmlParser;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class SortChain extends Command{
    public SortChain() throws XMLStreamException, FileNotFoundException {
        super(new TopChain());
    }
    Map<String, String> map = XmlParser.parseToMap();

    @Override
    public void execute (String request) throws XMLStreamException, FileNotFoundException {
        if (request.equals("sort")) {
            List<Product> allStoreProducts = Store.getStore().getAllProductList();
            allStoreProducts.sort(ComparatorHelper.compare(map));
            for (Product product : allStoreProducts) {
                System.out.println(product);
            }
        }
        else if (getNext() != null){
            getNext().execute(request);
        }
    }

}