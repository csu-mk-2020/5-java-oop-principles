package com.example.task04;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Objects;

public class Logger {
    private static final Hashtable<String, Logger> loggers = new Hashtable<>();
    private final String name;
    private MessageHandler handler;
    private LoggerLevel level = LoggerLevel.DEBUG;

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        return Logger.loggers.computeIfAbsent(name, Logger::new);
    }

    public MessageHandler getHandler() {
        return handler;
    }

    public void setHandler(MessageHandler handler) {
        Objects.requireNonNull(handler);
        this.handler = handler;
    }

    public String getName() {
        return this.name;
    }

    public LoggerLevel getLevel() {
        return this.level;
    }

    public void setLevel(LoggerLevel level) throws NullPointerException {
        Objects.requireNonNull(level);
        this.level = level;
    }

    public void log(LoggerLevel level, String text) {
        if (level.ordinal() >= this.level.ordinal()) {
            this.handler.handleMessage(String.format("[%s] %s %s - %s\n",
                    level.toString(),
                    new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(new Date()),
                    this.name,
                    text
            ));
        }
    }

    public void log(LoggerLevel level, String format, Object... args) {
        if (level.ordinal() >= this.level.ordinal()) {
            this.handler.handleMessage(String.format("[%s] %s %s - %s\n",
                    level.toString(),
                    new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(new Date()),
                    this.name,
                    String.format(format, args))
            );
        }
    }

    public void debug(String text) {
        this.log(LoggerLevel.DEBUG, text);
    }

    public void debug(String format, Object... args) {
        this.log(LoggerLevel.DEBUG, format, args);
    }

    public void info(String text) {
        this.log(LoggerLevel.INFO, text);
    }

    public void info(String format, Object... args) {
        this.log(LoggerLevel.INFO, format, args);
    }

    public void warning(String text) {
        this.log(LoggerLevel.WARNING, text);
    }

    public void warning(String format, Object... args) {
        this.log(LoggerLevel.WARNING, format, args);
    }

    public void error(String text) {
        this.log(LoggerLevel.ERROR, text);
    }

    public void error(String format, Object... args) {
        this.log(LoggerLevel.ERROR, format, args);
    }
}