package at.czlabinger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The Main class that creates the Communication object and main thread
 * @author  Zlabinger Christof
 * @version 1.1
 */
public class App {

  /**
   * Main method that creates the Communication object and the main thread
   * @param args not used
   */
  public static void main( String[] args ) {
    Communication c = new Communication(new MyCountdownLatch(3), new LinkedBlockingQueue<>());
    new Thread(new MainThread(c)).start();
  }
}
