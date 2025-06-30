package com.toohina.mq.consumer;

import com.toohina.mq.queue.MessageQueue;
import com.toohina.mq.queue.Message;

import java.util.HashSet;
import java.util.List;

public class Consumer implements Runnable{
    private final String id;
    private final List<MessageQueue> mqList;
    private HashSet<String> QueuesCompleted=new HashSet<>();

    public Consumer(String id, List<MessageQueue> mqList){
        this.id=id;
        this.mqList= mqList;
    }

    @Override
    public void run() {
        while(true) {
            for(MessageQueue mq: mqList) {
                if(QueuesCompleted.contains(mq.getName()))continue;
                Message message = mq.dequeue();
                if(message==null)continue;
                if (message.isPoisonPill()) {
                    System.out.println("Consumer " + id + " received poison pill from queue: "+mq.getName());
                    QueuesCompleted.add(mq.getName());
                    continue;
                }

                try {
                    Thread.sleep(500); // simulate processing
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Consumer with id: " + this.id + " has processed message with payload: " + message.getPayload());
                message.acknowledge();

                System.out.println("Consumer with id: " + this.id + " has acknowledged message with Id: " + message.getId()
                        +" from queue: "+mq.getName());
            }

            if(allQueuesCompleted()){
                System.out.println("Consumer with id: " + this.id + " has completed processing all subscribed queues");
                break;
            }
        }
    }

    public boolean allQueuesCompleted(){
        for(MessageQueue mq: mqList) {
            if(!QueuesCompleted.contains(mq.getName()))return false;
        }
        return true;
    }
}
