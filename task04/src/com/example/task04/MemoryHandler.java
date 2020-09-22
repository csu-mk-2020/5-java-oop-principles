package com.example.task04;

import java.util.ArrayDeque;

public class MemoryHandler implements MessageHandler{

    private final ArrayDeque<String> messages;
    private final MessageHandler messageHandler;
    private final int limit;

    public MemoryHandler(MessageHandler messageHandler, int limit) throws IllegalArgumentException{
        if (messageHandler instanceof MemoryHandler)
            throw new IllegalArgumentException("MemoryHandler is not allowed");
        if (limit <= 0)
            limit = 10;
        this.limit = limit;
        this.messages = new ArrayDeque<>(this.limit);
        this.messageHandler = messageHandler;
    }

    @Override
    public void handleMessage(String message) {
        this.messages.add(message);
        if (this.messages.size() >= this.limit){
            for (int i = 0; i < this.limit; ++i) {
                this.messageHandler.handleMessage(this.messages.poll());
            }
        }
    }

    public void flush(){
        while (this.messages.size() > 0){
            this.messageHandler.handleMessage(this.messages.poll());
        }
    }
}
