// BQProducerConsumerTest.java
package com.example.HomeLoanApp.collectiontutorial;

import java.util.concurrent.*;

public class BQProducerConsumerTest {
    public static void main(String[] args) {
        int capacity = 5;
        boolean fair = true;
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(capacity, fair);
//        BlockingQueue<String> queu1 = new LinkedBlockingQueue<>(capacity);
//        BlockingQueue<String> queue2 = new PriorityBlockingQueue<>(capacity);
//        BlockingQueue<String> queue3 = new SynchronousQueue<>(fair);//Unbounded
//        BlockingQueue<String> queue4 = new PriorityBlockingQueue<>(capacity);


        // Create one producer and two consumer and let them produce 
        // and consume indefinitely  
        new BQProducer(queue, "Producer1").start();
        new BQConsumer(queue, "Consumer1").start();
        new BQConsumer(queue, "Consumer2").start();
    }
}
