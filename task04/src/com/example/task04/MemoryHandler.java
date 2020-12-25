package com.example.task04;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class MemoryHandler implements MessageHandler {
    private final Queue<String> messages = new ArrayDeque<>();
    private final MessageHandler handler;
    private final long limit;

    public MemoryHandler(MessageHandler handler, long limit) {
        Objects.requireNonNull(handler);
        this.handler = handler;
        this.limit = limit;
    }

    @Override
    public void handle(String message) {
        Objects.requireNonNull(message);
        messages.add(message);
        if (messages.size() >= limit) {
            flush();
        }
    }

    public void flush() {
        while(messages.size() != 0) {
            handler.handle(messages.poll());
            messages.remove();
        }
    }
}
