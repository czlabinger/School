package at.czlabinger;

import java.util.concurrent.BlockingQueue;

/**
 * Class for handeling the communication between the main and the runner threads
 * @author  Zlabinger Christof
 * @version 1.1
 */
public class Communication {
    private MyCountdownLatch latch;
    private BlockingQueue<String> queue;

    /**
     * The Constructor for the Communication class
     * @param latch the CountDownLatch used to syncronize the runners
     * @param queue the BlockingQueue used for communication with the runners
     */
    public Communication(MyCountdownLatch latch, BlockingQueue<String> queue) {
        this.latch = latch;
        this.queue = queue;
    }

    /**
     * Puts a string into the queue
     * @param s the String to put in the queue
     * @throws InterruptedException if interrupted while waiting
     */
    public void put(String s) throws InterruptedException {
        this.queue.put(s);
    }

    /**
     * Takes a String out of the queue
     * @return the String that was taken from the queue
     * @throws InterruptedException if interrupted while waiting
     */
    public String take() throws InterruptedException {
        return this.queue.take();
    }

    /**
     * Counts down the latch and waits untill all threads are ready
     * @throws InterruptedException if interrupted while waiting
     */
    public void readyUp() throws InterruptedException {
        this.latch.countDown();
        this.latch.await();
    }
}
