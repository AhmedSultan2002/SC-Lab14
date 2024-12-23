package expressivo;


import java.util.concurrent.atomic.AtomicInteger;

// Task 4: Simulating a thread-safe bank account
public class BankTransaction {
    private AtomicInteger balance = new AtomicInteger(1000);

    public void deposit(int amount) {
        balance.addAndGet(amount);
        System.out.println(Thread.currentThread().getName() + " Deposited: " + amount + ", Balance: " + balance.get());
    }

    public void withdraw(int amount) {
        if (balance.get() >= amount) {
            balance.addAndGet(-amount);
            System.out.println(Thread.currentThread().getName() + " Withdrew: " + amount + ", Balance: " + balance.get());
        } else {
            System.out.println(Thread.currentThread().getName() + " Insufficient funds for withdrawal of: " + amount);
        }
    }

    public static void main(String[] args) {
        BankTransaction account = new BankTransaction();

        Runnable clientTask = () -> {
            for (int i = 0; i < 5; i++) {
                if (Math.random() > 0.5) {
                    account.deposit((int) (Math.random() * 200) + 1);
                } else {
                    account.withdraw((int) (Math.random() * 200) + 1);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread client1 = new Thread(clientTask, "Client1");
        Thread client2 = new Thread(clientTask, "Client2");
        Thread client3 = new Thread(clientTask, "Client3");

        client1.start();
        client2.start();
        client3.start();
    }
}
