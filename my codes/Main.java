public class Main {
    public static void main(String[] args) {
        CubbyHole cubbyhole = new CubbyHole(5);

        Producer p1 = new Producer(cubbyhole, 1);
        Consumer c1 = new Consumer(cubbyhole, 1);

        p1.start();
        c1.start();
    }
}
