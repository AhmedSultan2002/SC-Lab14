package expressivo;

// Task 1: Creating two threads to print numbers and their squares
public class MultithreadingDemo {
    public static void main(String[] args) {
        Thread numbersThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Number: " + i);
                try {
                    Thread.sleep(100); // Simulate some delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread squaresThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Square: " + (i * i));
                try {
                    Thread.sleep(100); // Simulate some delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        numbersThread.start();
        squaresThread.start();
    }
}
