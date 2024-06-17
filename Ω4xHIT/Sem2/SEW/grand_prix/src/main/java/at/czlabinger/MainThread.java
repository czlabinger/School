package at.czlabinger;

import java.util.ArrayList;
import java.util.Random;

/**
 * The main thread that starts the runners
 * @author  Zlabinger Christof
 * @version 1.0
 */
public class MainThread implements Runnable {

    private final Communication c;

    /**
     * Constructor for the main thread
     * @param c the Communication object
     */
    public MainThread(Communication c) {
        this.c = c;
    }

  /**
   * The run method starts the runners and prints the result for each round
   */
  @Override
  public void run() {

    ArrayList<Integer> caluclations1 = new ArrayList<>();
    ArrayList<Integer> calculations2 = new ArrayList<>();
    ArrayList<Integer> calculations3 = new ArrayList<>();

    for(int i = 0; i < 3; i++) {
      caluclations1.add(new Random().nextInt(1));
      calculations2.add(new Random().nextInt(1));
      calculations3.add(new Random().nextInt(1));
    }

    new Thread(new Runner("Runner1", caluclations1, c)).start();
    new Thread(new Runner("Runner2", calculations2, c)).start();
    new Thread(new Runner("Runner3", calculations3, c)).start();

    int position = 1;
    while (true) {
        try {
            String result = c.take();
            if(result.contains("position")) {
              result += position;
              position++;
            }
            System.out.println(result);
            if(position == 4) {
              break;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
  }
}
