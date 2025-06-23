package com.toohina.mq.consumer;

import com.toohina.mq.broker.MessageQueue;
import com.toohina.mq.core.Message;

public class Consumer implements Runnable{
    private final String id;
    private final MessageQueue mq;

    Consumer(String id,MessageQueue mq){
        this.id=id;
        this.mq=mq;
    }

    @Override
    public void run() {
        Message message=mq.dequeue();
        System.out.println("Consumer with id: "+this.id+ "has processed message with payload: "+ message.getPayload());

        try {
            Thread.sleep(500); // simulate processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        message.acknowledge();
        System.out.println("Consumer with id: "+this.id+ "has acknowledged message with Id: "+ message.getId());

    }
}
