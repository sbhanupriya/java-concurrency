package concurrencyBasics.threads;

public class ExecuteLong implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(20000);
            System.out.println("Thread created by implementing runnable interface " + Thread.currentThread().getId());
        } catch (InterruptedException ex){
            System.out.println("Thread is interrupted!!! " + Thread.currentThread().getId());
        }
    }
}
