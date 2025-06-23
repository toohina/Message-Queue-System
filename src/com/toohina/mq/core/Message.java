package com.toohina.mq.core;

public class Message {
    private final String id;
    private final String payload;
    private boolean acknowledged=false;

    public Message(String id, String payload) {
        this.id = id;
        this.payload = payload;
    }
    public void acknowledge(){acknowledged=true;}
    public String getPayload(){return this.payload;}
    public String getId(){return this.id;}
}
