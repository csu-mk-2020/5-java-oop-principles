package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

public class RotationFileHandler extends FileHandler {
    private final Duration interval;
    private LocalDateTime lastHandleTime = LocalDateTime.now();
    private long index = 1;
    private final Function<Long, String> fileNameGenerator;

    protected RotationFileHandler(Duration interval, Function<Long, String> fileNameGenerator) throws IOException {
        super(fileNameGenerator.apply((long) 1));
        this.fileNameGenerator = fileNameGenerator;
        this.interval = interval;
    }

    public RotationFileHandler Create(Duration interval, Function<Long, String> fileNameGenerator) throws IOException {
        Objects.requireNonNull(interval);
        Objects.requireNonNull(fileNameGenerator);
        return new RotationFileHandler(interval, fileNameGenerator);
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(lastHandleTime, now).compareTo(interval) >= 0) {
            try {
                writer.close();
                writer = new FileWriter(this.fileNameGenerator.apply(++index));
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        lastHandleTime = now;
        super.handle(message);
    }
}
