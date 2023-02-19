package com.coherentsolutions.consoleApp;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public abstract class Command{
    private Command next;

    public Command(Command next){
        this.next = next;
    }

    public Command getNext(){
        return next;
    }

    public abstract void execute (String request) throws XMLStreamException, FileNotFoundException;
}
