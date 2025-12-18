package interviewProblems.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {
    private Queue<Integer> queue;
    private int maxLimit;
    private int produceCount;
    private int consumeCount;
    public DataQueue(int limit){
        queue = new LinkedList<>();
        maxLimit = limit;
    }
    private boolean isFull(){
        return queue.size()==maxLimit;
    }
    private boolean isEmpty(){
        return queue.size()==0;
    }
    public synchronized void addItem(Integer data) throws InterruptedException {
        while(isFull()){
            wait();
        }
        System.out.println("Add item  "+ data);
        produceCount++;
        queue.offer(data);
        notifyAll();
    }

    public synchronized void consumeItem() throws InterruptedException {
        while(isEmpty()){
            wait();
        }
        consumeCount++;
        System.out.println("Consume  "+ queue.poll());
        notifyAll();
    }
    public void result(){
        System.out.println("The result is " + produceCount+ " " + consumeCount);
    }
}
