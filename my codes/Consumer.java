import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {
    private final CubbyHole cubbyhole; // Import CubbyHole class
    private final int number;

    public Consumer(CubbyHole cubbyhole, int number) {
        this.cubbyhole = cubbyhole;
        this.number = number;
    }

    public void run() {
        int value = 0;
        for (int i = 0; i < 10; i++) {
            try {
                value = (int) cubbyhole.get(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
