package interviewProblems.orderedPrinting;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderedPrinting {

    private Lock lock;
    private Condition[] conditions;
    private boolean[] canRun;
    private int size;

    public OrderedPrinting(int n){
        lock = new ReentrantLock();
        conditions = new Condition[n];
        canRun = new boolean[n];
        for(int i=0;i<n;i++){
            conditions[i] = lock.newCondition();
            canRun[i] = false;
        }
        size = n;
        canRun[0] = true;
    }

    public void print(int n){
        lock.lock();
        try {
            while(canRun[n]==false){
                conditions[n].await();
            }
            System.out.println( n == 0 ? "First" : n == 1 ? "Second" : "Third");
            canRun[n]=false;
            canRun[(n+1)%size]=true;
            conditions[(n+1)%size].signal();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
