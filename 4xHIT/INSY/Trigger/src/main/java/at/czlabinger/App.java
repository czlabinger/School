package at.czlabinger;

import java.io.IOException;
import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        Server webshop = new Server();
        webshop.start();
        System.out.println("Webshop running at http://127.0.0.1:8000");
    }
}
