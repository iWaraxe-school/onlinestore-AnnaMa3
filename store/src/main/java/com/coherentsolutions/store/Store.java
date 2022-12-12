package com.coherentsolutions.store;
import com.coherentsolutions.domain.Category;
import com.coherentsolutions.store.StoreHelper.XMLParser.XmlParser;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {

    private List<Category> categoryList= new ArrayList<>();

    public Store() throws XMLStreamException, FileNotFoundException {
    }

    public void addCategory (Category category) {
       categoryList.add(category);
    }

    Map<String,String> map = XmlParser.parseToMap();

    public void printAll() {
        for (Category category : categoryList) {
            category.compare(map);
            category.printProducts();
        }
    }


}
