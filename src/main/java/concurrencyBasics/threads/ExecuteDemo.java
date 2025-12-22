package concurrencyBasics.threads;

public class ExecuteDemo {
    public static void main(String[] args) throws InterruptedException {

        Thread[] tasks = new Thread[3];
        tasks[0]= new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread created as anonymous class " + Thread.currentThread().getId());
            }
        });

        tasks[1] = new Thread(new ExecuteLong());
        tasks[2] = new Thread(new ExecuteShort());

        for(Thread thread: tasks) {
            thread.start();
        }

        System.out.println("Execution starts with main thread " + Thread.currentThread().getId());

        for(Thread thread: tasks) {
            thread.join();
        }
    }
}
