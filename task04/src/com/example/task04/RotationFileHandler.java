package com.example.task04;

import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RotationFileHandler extends FileHandler implements Closeable {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final Semaphore semaphore = new Semaphore(1, true);

    @Override
    public void close() {
        try {
            semaphore.acquire();
            this.executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private class Task implements Runnable {
        private int i = 1;

        @Override
        public void run() {
            try {
                semaphore.acquire();
                fileWriter.close();
                fileWriter = new FileWriter("log" + i);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            ++i;
        }
    }

    public RotationFileHandler(long delay, TimeUnit unit) throws IOException {
        super();
        if (delay < 0)
            throw new IllegalArgumentException("delay must be >= 0");
        Objects.requireNonNull(unit);
        this.executor.scheduleWithFixedDelay(new Task(), delay, delay, unit);
    }

    @Override
    public void handleMessage(String message) {
        try {
            semaphore.acquire();
            super.handleMessage(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

}
