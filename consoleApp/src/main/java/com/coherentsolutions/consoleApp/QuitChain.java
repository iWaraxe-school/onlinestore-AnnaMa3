package com.coherentsolutions.consoleApp;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class QuitChain extends Command{
    public QuitChain() throws XMLStreamException, FileNotFoundException {
        super(null);
    }

    @Override
    public void execute (String request) throws XMLStreamException, FileNotFoundException {
        if (request.equals("quit")) {
            System.exit(0);
        }
        else {
            System.out.println("Wrong command");
        }
    }

}