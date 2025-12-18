package interviewProblems.producerConsumer;

public class ProducerConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        DataQueue queue = new DataQueue(100);
        for(int i=1;i<=3000;i++){
            int temp = i;
            Thread thread = new Thread(() -> {
                try {
                    queue.addItem(temp);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }

        for(int i=1;i<=3000;i++){
            Thread thread = new Thread(() -> {
                try {
                   queue.consumeItem();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }

        Thread.sleep(1000);
        queue.result();
    }
}
