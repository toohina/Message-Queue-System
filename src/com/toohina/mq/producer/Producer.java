package com.toohina.mq.producer;

import com.toohina.mq.broker.MessageQueue;
import com.toohina.mq.core.Message;

public class Producer {
    private final String id;
    private Message message;
    private MessageQueue mq;

    public Producer(String id) {
        this.id = id;
    }

    public void publish(Message message, MessageQueue mq){

    }
}
