package com.coherentsolutions.consoleApp;

import com.sun.net.httpserver.HttpContext;
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

        HttpContext contextMainPage = server.createContext("/", new RootHandler());
        HttpContext contextStorePage = server.createContext("/store", new MainPage(storeDAO));

        contextMainPage.setAuthenticator(new Authenticator("get"));
        contextStorePage.setAuthenticator(new Authenticator("get"));


        server.setExecutor(null);
        server.start();

    }
}
