package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class RotationFileHandler extends FileHandler {
    private final Duration interval;
    private LocalDateTime lastHandleTime = LocalDateTime.now();
    private long index = 1;

    public RotationFileHandler(Duration interval) throws IOException {
        super("log1.txt");
        Objects.requireNonNull(interval);
        this.interval = interval;
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(lastHandleTime, now).compareTo(interval) >= 0) {
            try {
                writer.close();
                writer = new FileWriter("log" + ++index + ".txt");
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        lastHandleTime = now;
        super.handle(message);
    }
}
