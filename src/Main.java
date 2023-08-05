import Handlers.RomanNumeralHandler;
import Handlers.RootHandler;
import Handlers.StopHandler;
import Models.RomanNumeral;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) {
       try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", new RootHandler());
            server.createContext("/stop", new StopHandler(server));
            server.createContext("/romannumeral", new RomanNumeralHandler());
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}