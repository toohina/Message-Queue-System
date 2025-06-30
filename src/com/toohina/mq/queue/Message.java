package com.toohina.mq.queue;

import java.util.UUID;

public class Message {
    private final UUID id;
    private final String payload;
    private boolean acknowledged;
    private boolean poisonPill =false;

    public Message(UUID id, String payload, boolean poisonPill) {
        this.id = id;
        this.payload = payload;
        this.acknowledged=false;
        this.poisonPill =poisonPill;
    }
    public void acknowledge(){acknowledged=true;}
    public String getPayload(){return this.payload;}
    public UUID getId(){return this.id;}
    public boolean isPoisonPill(){return poisonPill;}
}
