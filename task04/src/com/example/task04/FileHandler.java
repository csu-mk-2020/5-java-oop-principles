package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements MessageHandler {

    protected FileWriter fileWriter;

    public FileHandler(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public FileHandler() throws IOException {
        this.fileWriter = new FileWriter("log");
    }

    @Override
    public void handleMessage(String message) {
        try {
            this.fileWriter.write(message + "\n");
            this.fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
