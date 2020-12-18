package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

class FileHandlerCloser implements Runnable {

    private final FileWriter fileWriter;

    FileHandlerCloser(FileWriter fileWriter) {
        this.fileWriter = Objects.requireNonNull(fileWriter);
    }

    @Override
    public void run() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class FileHandler implements MessageHandler {

    protected FileWriter fileWriter;

    public FileHandler(FileWriter fileWriter) {
        this.fileWriter = Objects.requireNonNull(fileWriter);
    }

    public FileHandler() throws IOException {
        this.fileWriter = new FileWriter("log");
        Runtime.getRuntime().addShutdownHook(new Thread(new FileHandlerCloser(this.fileWriter)));
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
