package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Objects;

public class Logger {
    private static final Hashtable<String, Logger> loggers = new Hashtable<>();
    private final String name;
    private LoggerLevel level = LoggerLevel.ERROR;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy.MM.dd hh:mm:ss ");

    protected Logger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Logger getLogger(String name) {
        return Logger.loggers.computeIfAbsent(name, Logger::new);
    }

    public void setLevel(LoggerLevel level) {
        this.level = Objects.requireNonNull(level);
    }

    public LoggerLevel getLevel() {
        return level;
    }

    void log(LoggerLevel level, String message) {
        if (level.ordinal() >= this.level.ordinal()) {
            System.err.println("[" + this.level.toString() + "]" + dateFormat.format(new Date()) +
                    this.name + " - " + message);
        }
    }

    void log(LoggerLevel level, String format, Object... objects) {
        if (level.ordinal() >= this.level.ordinal()) {
            System.err.println("[" + this.level.toString() + "]" + dateFormat.format(new Date()) +
                    this.name + " - " + String.format(format, objects));
        }
    }

    void debug(String message) {
        this.log(LoggerLevel.DEBUG, message);
    }

    void debug(String format, Object... objects) {
        this.log(LoggerLevel.DEBUG, format, objects);
    }

    void info(String message) {
        this.log(LoggerLevel.INFO, message);
    }

    void info(String format, Object... objects) {
        this.log(LoggerLevel.INFO, format, objects);
    }

    void warning(String message) {
        this.log(LoggerLevel.WARNING, message);
    }

    void warning(String format, Object... objects) {
        this.log(LoggerLevel.WARNING, format, objects);
    }

    void error(String message) {
        this.log(LoggerLevel.ERROR, message);
    }

    void error(String format, Object... objects) {
        this.log(LoggerLevel.ERROR, format, objects);
    }

}
