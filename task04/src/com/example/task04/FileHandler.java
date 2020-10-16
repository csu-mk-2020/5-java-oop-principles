package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class FileHandler implements MessageHandler {
    protected FileWriter writer;

    public FileHandler(String filePath) throws IOException {
        Objects.requireNonNull(filePath);
        writer = new FileWriter(filePath);
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

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        writer.close();
    }
}
