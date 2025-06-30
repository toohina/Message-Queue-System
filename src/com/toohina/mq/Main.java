package com.toohina.mq;

import com.toohina.mq.broker.MessageQueue;
import com.toohina.mq.consumer.Consumer;
import com.toohina.mq.producer.Producer;

public class Main {
    public static void main(String[] args) {
        MessageQueue mq=new MessageQueue("payment tasks");

        Producer P1=new Producer("P1");

        Thread C1=new Thread(new Consumer("C1",mq));
        Thread C2=new Thread(new Consumer("C2",mq));
        C1.start();
        C2.start();

        for (int i = 0; i < 10; i++) {
            P1.publish("Task #"+i,mq,false);
            try{
                Thread.sleep(300);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        //Send as many poison pills as the number of consumers
        P1.publish("Poison Pill",mq,true);
        P1.publish("Poison Pill2",mq,true);

    }
}
