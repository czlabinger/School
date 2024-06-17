package at.czlabinger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class App  {
    
  public static void main( String[] args ) throws IOException, ClassNotFoundException {
    System.out.println("Connecting to: " + args[0] + ":" + args[1]);

    String host = args[0];
    int port = Integer.parseInt(args[1]);

    Socket s = new Socket(host, port);
    ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
    ObjectInputStream is = new ObjectInputStream(s.getInputStream());

    while(true) {
      String input = (String) is.readObject();
      System.out.println(input);

      if(input.contains("richtig beantwortet")) {
        break;
      }

      Scanner sc = new Scanner(System.in);
      os.writeObject(sc.nextLine().strip());

      System.out.println((String) is.readObject());

    }

    is.close();
    os.close();
    s.close();
  }
}
