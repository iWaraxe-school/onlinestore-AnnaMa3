package com.coherentsolutions.store.StoreHelper.XMLParser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class XmlParser  {

    public static Map<String, String> parseToMap() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileReader("store/src/main/resources/config.xml"));
        streamReader.nextTag();
        System.out.println(streamReader.getLocalName());
        streamReader.nextTag();
        System.out.println(streamReader.getLocalName());

        Map<String,String> map = new TreeMap<>();

        while(!streamReader.isEndElement()) {

                String key = streamReader.getLocalName();
                System.out.println("key:" + key);
                String value = streamReader.getElementText();
                System.out.println("value:" +value);
                map.put(key, value);

                streamReader.nextTag();
        }

        return map;
    }


}
