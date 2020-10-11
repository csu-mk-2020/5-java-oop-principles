package com.example.task04;

import java.util.ArrayDeque;
import java.util.Objects;

public class MemoryHandler implements MessageHandler {

    private final ArrayDeque<String> msgs;
    private final MessageHandler handler;
    private final int capacity;

    public MemoryHandler(MessageHandler handler, int capacity) throws IllegalArgumentException {
        Objects.requireNonNull(handler);
        if (handler instanceof MemoryHandler) {
            throw new IllegalArgumentException("Handler cannot be instance of MemoryHandler");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        this.capacity = capacity;
        this.handler = handler;
        this.msgs = new ArrayDeque<>(capacity);
    }

    @Override
    public void handleMessage(String message) {
        this.msgs.add(message);
        if (this.msgs.size() >= this.capacity) {
            for (String msg : this.msgs) {
                this.handler.handleMessage(msg);
            }
        }
    }

    public void flush() {
        for (String msg : this.msgs) {
            this.handler.handleMessage(msg);
        }
    }
}
