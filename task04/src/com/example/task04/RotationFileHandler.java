package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class ScheduledExecutorServiceCloser implements Runnable {
    private final ScheduledExecutorService executor;

    ScheduledExecutorServiceCloser(ScheduledExecutorService executor) {
        this.executor = Objects.requireNonNull(executor);
    }

    @Override
    public void run() {
        this.executor.shutdown();
    }
}

public class RotationFileHandler extends FileHandler {

    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private static int InstanceCounter = 0;
    private final Semaphore semaphore = new Semaphore(1, true);

    private class Task implements Runnable {
        private int i = 1;

        @Override
        public void run() {
            try {
                semaphore.acquire();
                try {
                    fileWriter.close();
                    fileWriter = new FileWriter("log" + i);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++i;
        }
    }

    public RotationFileHandler(long delay, TimeUnit unit) throws IOException {
        super();
        if (delay < 0)
            throw new IllegalArgumentException("delay must be >= 0");
        Objects.requireNonNull(unit);
        RotationFileHandler.executor.scheduleWithFixedDelay(new Task(), delay, delay, unit);
        if (RotationFileHandler.InstanceCounter == 0) {
            Runtime.getRuntime().addShutdownHook(
                    new Thread(new ScheduledExecutorServiceCloser(RotationFileHandler.executor)));
        }
        ++RotationFileHandler.InstanceCounter;
    }

    @Override
    public void handleMessage(String message) {
        try {
            semaphore.acquire();
            super.handleMessage(message);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
