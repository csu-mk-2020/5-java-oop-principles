package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class FileHandler implements MessageHandler {

    protected FileWriter fw;

    public FileHandler() throws IOException {
        this.fw = new FileWriter(String.format("logfile%s", new Date()));
    }

    public FileHandler(FileWriter fw) {
        Objects.requireNonNull(fw);
        this.fw = fw;
    }

    @Override
    public void handleMessage(String message) {
        try {
            this.fw.write(message + '\n');
            this.fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
