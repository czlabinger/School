package at.czlabinger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App  {

  public static void main( String[] args )  {
    System.out.println("Port: " + args[0]);
    int port = Integer.parseInt(args[0]);

      try (ServerSocket s = new ServerSocket(port)) {
          System.out.println("Waiting for clients to connect...");
          while (true) {
              Socket client = s.accept();
              System.out.println("Client connected");
              new Thread(new ClientConnection(client)).start();
          }
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
  }
}
