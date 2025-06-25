package com.toohina.mq.core;

import java.util.UUID;

public class Message {
    private final UUID id;
    private final String payload;
    private boolean acknowledged;

    public Message(UUID id, String payload) {
        this.id = id;
        this.payload = payload;
        this.acknowledged=false;
    }
    public void acknowledge(){acknowledged=true;}
    public String getPayload(){return this.payload;}
    public UUID getId(){return this.id;}
}
