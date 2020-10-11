package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class RotationFileHandler extends FileHandler {
    private final Duration _interval;
    private LocalDateTime _lastHandleTime = LocalDateTime.now();
    private long _index = 1;

    public RotationFileHandler(Duration interval) throws IOException {
        super("log1.txt");
        Objects.requireNonNull(interval);
        _interval = interval;
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(_lastHandleTime, now).compareTo(_interval) >= 0) {
            try {
                _writer.close();
                _writer = new FileWriter("log" + _index++ + ".txt");
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        _lastHandleTime = now;
        super.handle(message);
    }
}
