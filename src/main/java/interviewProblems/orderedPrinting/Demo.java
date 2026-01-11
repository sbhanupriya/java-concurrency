package interviewProblems.orderedPrinting;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        OrderedPrinting orderedPrinting = new OrderedPrinting(3);
        ExecutorService executors = Executors.newFixedThreadPool(3);

        executors.submit(() -> {
            for (int i = 0; i < 10; i++) orderedPrinting.print(0);
        });

        executors.submit(() -> {
            for (int i = 0; i < 10; i++) orderedPrinting.print(1);
        });

        executors.submit(() -> {
            for (int i = 0; i < 10; i++) orderedPrinting.print(2);
        });

        executors.shutdown();
        executors.awaitTermination(10, TimeUnit.SECONDS);
    }
}
