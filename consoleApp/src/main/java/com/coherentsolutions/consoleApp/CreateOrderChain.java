package com.coherentsolutions.consoleApp;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class CreateOrderChain extends Command{
    public CreateOrderChain() throws XMLStreamException, FileNotFoundException {
        super(new QuitChain());
    }

    @Override
    public void execute (String request) throws XMLStreamException, FileNotFoundException {
        if (request.equals("order")) {
            CreateOrder order = new CreateOrder();
            order.start();
        }
        else if (getNext() != null){
            if (request.equals("quit")) {
                getNext().execute(request);
            }
            else {
                System.out.println("Wrong command");
            }
        }
    }

}
