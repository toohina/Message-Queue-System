package com.toohina.mq.producer;

import com.toohina.mq.broker.MessageQueue;
import com.toohina.mq.core.Message;

import java.util.UUID;

public class Producer {
    private final String id;

    public Producer(String id) {
        this.id = id;
    }

    public void publish(String payload, MessageQueue mq, boolean isPoison){
        Message message=new Message(UUID.randomUUID(),payload,isPoison);
        mq.enqueue(message);
        System.out.println("Producer " + id + " published: " + payload);
    }
}
