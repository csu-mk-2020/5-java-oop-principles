package com.example.task04;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class MemoryHandler implements MessageHandler {
    private final Queue<String> _messages = new ArrayDeque<>();
    private final MessageHandler _handler;
    private final long _limit;

    public MemoryHandler(MessageHandler handler, long limit) {
        Objects.requireNonNull(handler);
        _handler = handler;
        _limit = limit;
    }

    @Override
    public void handle(String message) {
        Objects.requireNonNull(message);
        _messages.add(message);
        if (_messages.size() >= _limit) {
            flush();
        }
    }

    public void flush() {
        while(_messages.size() != 0) {
            _handler.handle(_messages.poll());
        }
    }
}
