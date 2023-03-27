package com.coherentsolutions.consoleApp;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class Server {


    public static void startServer() throws IOException, SQLException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        System.out.println("Server starting");

        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        StoreDAO storeDAO = new StoreDAO(dataBaseHandler);

        server.createContext("/", new RootHandler());
        server.createContext("/store", new MainPage(storeDAO));

        server.setExecutor(null);
        server.start();

    }
}
