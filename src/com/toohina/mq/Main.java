package com.toohina.mq;

import com.toohina.mq.queue.MessageQueue;
import com.toohina.mq.consumer.Consumer;
import com.toohina.mq.producer.Producer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MessageQueue mq1=new MessageQueue("payment tasks");
        MessageQueue mq2=new MessageQueue("storage tasks");
        MessageQueue mq3=new MessageQueue("read query tasks");
        MessageQueue mq4=new MessageQueue("write query tasks");

        List<MessageQueue> mqList=Arrays.asList(mq1,mq2,mq3,mq4);

        Producer P1=new Producer("P1");

        List<MessageQueue> queuesSubscribedByC1=Arrays.asList(mq1,mq2,mq3);
        List<MessageQueue> queuesSubscribedByC2=Arrays.asList(mq2,mq4);

        Thread C1=new Thread(new Consumer("C1",queuesSubscribedByC1));
        Thread C2=new Thread(new Consumer("C2",queuesSubscribedByC2));
        C1.start();
        C2.start();

        for (int i = 0; i < 3; i++) {
            for(MessageQueue mq:mqList){
                P1.publish("Task #"+i,mq,false);
                try{
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
        //Send as many poison pills as the number of consumers
        for(MessageQueue mq:queuesSubscribedByC1){
            P1.publish("Poison Pill",mq,true);
        }
        for(MessageQueue mq:queuesSubscribedByC2){
            P1.publish("Poison Pill",mq,true);
        }
    }
}
