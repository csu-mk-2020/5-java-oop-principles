package com.example.task04;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Objects;

public class Logger {
    private static final Hashtable<String, Logger> loggers = new Hashtable<>();
    private static final ArrayList<MessageHandler> handlers = new ArrayList<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy.MM.dd hh:mm:ss ");
    private final String name;
    private LoggerLevel level = LoggerLevel.ERROR;

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

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    void log(LoggerLevel level, String message) {
        if (level.ordinal() >= this.level.ordinal()) {
            for (MessageHandler handler : handlers) {
                handler.handleMessage("[" + this.level.toString() + "]"
                        + dateFormat.format(new Date()) + this.name + " - " + message);
            }
        }
    }

    void log(LoggerLevel level, String format, Object... objects) {
        if (level.ordinal() >= this.level.ordinal()) {
            for (MessageHandler handler : handlers) {
                handler.handleMessage("[" + this.level.toString() + "]"
                        + dateFormat.format(new Date()) + this.name + " - "
                        + String.format(format, objects));
            }
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
