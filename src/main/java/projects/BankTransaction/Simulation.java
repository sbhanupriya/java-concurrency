package projects.BankTransaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Simulation {
    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();
        bank.openAccount("Bhanu",1000);
        bank.openAccount("Arjun", 0);

        ExecutorService executor = Executors.newFixedThreadPool(100);

        for(int i=0;i<1000;i++)
        executor.submit(() -> bank.transfer(1,2,1));

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        bank.balance(1);
        bank.balance(2);


    }
}
