package com.example.task04;

import java.io.PrintStream;

public class ConsoleHandler implements MessageHandler {

    @Override
    public void handleMessage(String message) {
        System.err.println(message);
    }
}
