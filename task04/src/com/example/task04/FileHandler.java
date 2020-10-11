package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class FileHandler implements MessageHandler {
    protected FileWriter _writer;

    public FileHandler(String filePath) throws IOException {
        Objects.requireNonNull(filePath);
        _writer = new FileWriter(filePath);
    }

    @Override
    public void handle(String message) {
        try {
            _writer.write(message + '\n');
            _writer.flush();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        _writer.close();
    }
}
