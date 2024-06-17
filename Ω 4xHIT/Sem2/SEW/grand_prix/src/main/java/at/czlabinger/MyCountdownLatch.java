package at.czlabinger;

/**
 * A custom implementation of a CountdownLatch
 * @author  Zlabinger Christof
 * @version 1.0
 */
public class MyCountdownLatch {

    // Lock object used for synchronization
    private final Object lock = new Object();

    private int countdown;

    /**
     * Constructor
     * @param countdown the number of operations that need to complete
     */
    public MyCountdownLatch(int countdown) {
        this.countdown = countdown;
    }

    /**
     * Decrements the count of the latch releasing all waiting threads if the count reaches zero.
     */
    public void countDown() {
        synchronized (lock) {
            if (countdown >   0) {
                countdown--;
                if (countdown ==   0) {
                    // Notify all waiting threads that the count has reached zero
                    lock.notifyAll();
                }
            }
        }
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero
     * @throws InterruptedException if the current thread is interrupted
     */
    public void await() throws InterruptedException {
        synchronized (lock) {
            while (countdown >   0) {
                // Wait until the countdown reaches zero
                lock.wait();
            }
        }
    }
}

