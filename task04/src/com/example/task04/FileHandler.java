package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class FileHandler implements MessageHandler {
    protected FileWriter writer;

    public FileHandler(String filePath) throws IOException {
        Objects.requireNonNull(filePath);
        writer = new FileWriter(filePath);
        Runtime.getRuntime().addShutdownHook(new Thread(this::closeWriter));
    }

    private void closeWriter() {
        try {
            this.writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(String message) {
        try {
            writer.write(message + '\n');
            writer.flush();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
