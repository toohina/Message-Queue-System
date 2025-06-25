package com.toohina.mq.broker;

import com.toohina.mq.core.Message;
import java.util.*;

public class MessageQueue {
    private final String name;
    private final Queue<Message> messages;

    public MessageQueue(String name){
        this.name=name;
        this.messages=new LinkedList<>();
    }

    public synchronized void enqueue(Message message){
        //synchronized since multiple producers and consumers can read/write to the same queue
        messages.add(message);
        notifyAll();
    }
    public synchronized Message dequeue(){
        //synchronized since multiple producers and consumers can read/write to the same queue
        while(messages.isEmpty()){
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); //detailed explanation for this in notion
            }
        }
        return messages.remove();
    }
}
