package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RotationFileHandler extends FileHandler implements MessageHandler {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final Semaphore s = new Semaphore(1, true);

    public RotationFileHandler(long delay, TimeUnit unit) throws IOException, IllegalArgumentException {
        super();
        Objects.requireNonNull(unit);
        if (delay < 0) {
            throw new IllegalArgumentException("Delay must be positive");
        }

        this.scheduler.scheduleAtFixedRate(() -> {
            try {
                s.acquire();
                fw.close();
                fw = new FileWriter(String.format("log%s", new Date()));
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }, delay, delay, unit);
    }

    public void close() {
        try {
            this.s.acquire();
            scheduler.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            s.release();
        }
    }

    @Override
    public void handleMessage(String message) {
        try {
            this.s.acquire();
            super.handleMessage(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.s.release();
        }
    }
}
