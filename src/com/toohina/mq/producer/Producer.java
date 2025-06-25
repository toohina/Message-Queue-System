package com.toohina.mq.producer;

import com.toohina.mq.broker.MessageQueue;
import com.toohina.mq.core.Message;

import java.util.UUID;

public class Producer {
    private final String id;
    private Message message;
    private MessageQueue mq;

    public Producer(String id) {
        this.id = id;
    }

    public void publish(String payload, MessageQueue mq){
        Message message=new Message(UUID.randomUUID(),payload);
        mq.enqueue(message);
        System.out.println("Producer " + id + " published: " + payload);
    }
}
