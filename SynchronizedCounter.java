package expressivo;


// Task 2: Synchronizing a shared counter
public class SynchronizedCounter {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        SynchronizedCounter counter = new SynchronizedCounter();

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + counter.getCounter());
    }
}
