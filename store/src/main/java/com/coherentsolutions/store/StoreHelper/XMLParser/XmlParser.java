package com.coherentsolutions.store.StoreHelper.XMLParser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class XmlParser  {

    public static Map<String, String> parseToMap() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileReader("store/src/main/resources/config.xml"));
        streamReader.nextTag();
        streamReader.nextTag();
        Map<String,String> map = new LinkedHashMap<>();

        while(!streamReader.isEndElement()) {
                String key = streamReader.getLocalName();
                String value = streamReader.getElementText();
                map.put(key, value);
                streamReader.nextTag();
        }

        return map;
    }


}
