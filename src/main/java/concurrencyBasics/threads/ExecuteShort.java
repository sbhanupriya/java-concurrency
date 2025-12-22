package concurrencyBasics.threads;

public class ExecuteShort extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Thread created by Extend Thread Base class " + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
