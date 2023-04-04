package com.coherentsolutions.consoleApp;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MainPage implements HttpHandler {
    private  final StoreDAO storeDAO;

    public MainPage(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String dbStore = storeDAO.getAllProducts();
        String response = "<h1>All products in the store <br><br>" + dbStore + "</h1>";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
