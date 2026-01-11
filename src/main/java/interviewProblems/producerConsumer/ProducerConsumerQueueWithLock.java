package interviewProblems.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerQueueWithLock<T> {
    private Queue<T> queue;
    private int maxLimit;
    private int produceCount;
    private int consumeCount;
    private Lock lock;
    private Condition producerCondition;
    private Condition consumerCondition;
    public ProducerConsumerQueueWithLock(int limit){
        queue = new LinkedList<>();
        maxLimit = limit;
        lock = new ReentrantLock();
        producerCondition = lock.newCondition();
        consumerCondition = lock.newCondition();
    }
    private boolean isFull(){
        return queue.size()==maxLimit;
    }
    private boolean isEmpty(){
        return queue.size()==0;
    }
    public void addItem(T data) throws InterruptedException {
        lock.lock();
        while(isFull()){
            producerCondition.await();
        }
        System.out.println("Add item  "+ data);
        produceCount++;
        queue.offer(data);
        consumerCondition.signal();
        lock.unlock();
    }

    public T consumeItem() throws InterruptedException {
        lock.lock();
        while(isEmpty()){
            consumerCondition.await();
        }
        consumeCount++;
        T data = queue.poll();
        System.out.println("Consume  "+ data);
        producerCondition.signal();
        lock.unlock();
        return data;
    }
    public void result(){
        System.out.println("The result is " + produceCount+ " " + consumeCount);
    }
}
