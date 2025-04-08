public class Producer extends Thread {
    private final CubbyHole cubbyhole;
    private final int number;

    public Producer(CubbyHole cubbyhole, int number) {
        this.cubbyhole = cubbyhole;
        this.number = number;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                cubbyhole.put(number, i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
