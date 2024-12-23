package expressivo;


import java.util.concurrent.CopyOnWriteArrayList;

// Task 3: Concurrent access to a shared list
public class ConcurrentListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> sharedList = new CopyOnWriteArrayList<>();

        Runnable writerTask = () -> {
            for (int i = 0; i < 10; i++) {
                sharedList.add(Thread.currentThread().getName() + " - Item " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable readerTask = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " Reading: " + sharedList);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread writer1 = new Thread(writerTask, "Writer1");
        Thread writer2 = new Thread(writerTask, "Writer2");
        Thread reader = new Thread(readerTask, "Reader");

        writer1.start();
        writer2.start();
        reader.start();
    }
}
