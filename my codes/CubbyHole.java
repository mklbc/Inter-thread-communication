import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CubbyHole {
    private final int capacity;
    private final Object[] buffer;
    private int head = 0;
    private int tail = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public CubbyHole(int capacity) {
        this.capacity = capacity;
        this.buffer = new Object[capacity];
    }

    public void put(int who, Object value) throws InterruptedException {
        lock.lock();
        try {
            while ((tail + 1) % capacity == head) {
                notFull.await();
            }
            buffer[tail] = value;
            tail = (tail + 1) % capacity;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
        System.out.format("Producer %d put: %s%n", who, value);
    }

    public Object get(int who) throws InterruptedException {
        lock.lock();
        try {
            while (head == tail) {
                notEmpty.await();
            }
            Object value = buffer[head];
            head = (head + 1) % capacity;
            notFull.signal();
            return value;
        } finally {
            lock.unlock();
        }
    }
}
