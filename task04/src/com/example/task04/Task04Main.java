package com.example.task04;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Task04Main {

    public static void testMemoryHandler() {
        Logger logger = Logger.getLogger("test1");
        logger.setLevel(LoggerLevel.INFO);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        MemoryHandler memoryHandler = new MemoryHandler(consoleHandler, 10);
        logger.addHandler(memoryHandler);

        int i;
        for (i = 1; i <= 9; i++) {
            logger.info("info" + i);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 5 sec sleep:");
        logger.debug("debug" + i);
        logger.info("info" + i);
    }

    public static void testRotationFileHandler() {
        Logger logger = Logger.getLogger("test2");
        logger.setLevel(LoggerLevel.INFO);
        RotationFileHandler rotationFileHandler;
        try {
            rotationFileHandler = new RotationFileHandler(5, TimeUnit.SECONDS);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        logger.addHandler(rotationFileHandler);
        logger.info("log1");
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("log2");
    }

    public static void main(String[] args) {
        //testMemoryHandler();
        testRotationFileHandler();
    }
}
