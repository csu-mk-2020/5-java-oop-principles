package com.example.task04;

import java.text.SimpleDateFormat;
import java.util.*;

public class Logger {
    private final String name;
    private static final Map<String, Logger> loggers = new Hashtable<>();
    private LoggerLevel level = LoggerLevel.DEBUG;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    private final List<MessageHandler> handlers = new ArrayList<MessageHandler>();

    private void log(LoggerLevel level, String message) throws NullPointerException {
        Objects.requireNonNull(message);
        if (!canBeLogged(level)) {
            return;
        }
        if (handlers.size() == 0) {
            throw new IllegalStateException("You didn't register any handlers for logger with name " + name);
        }
        for(MessageHandler handler: handlers) {
            handler.handle(
                String.format(
                        "[%s] %s %s - %s%n",
                        level.toString(),
                        dateFormat.format(new Date()),
                        name,
                        message
                )
            );
        }
    }

    protected boolean canBeLogged(LoggerLevel level) {
        Objects.requireNonNull(level);
        return this.level.ordinal() <= level.ordinal();
    }

    private void log(LoggerLevel level, String format, Object... objects) {
        log(level, String.format(format, objects));
    }

    protected Logger(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        loggers.put(name, this);
    }

    public static Logger getLogger(String name) {
        Objects.requireNonNull(name);
        if (!loggers.containsKey(name)) {
            loggers.computeIfAbsent(name, Logger::new);
        }
        return loggers.get(name);
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public String getName() {
        return name;
    }

    public LoggerLevel getLevel() {
        return level;
    }

    public void setLevel(LoggerLevel level) {
        Objects.requireNonNull(level);
        this.level = level;
    }

    public void debug(String message) {
        log(LoggerLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LoggerLevel.INFO, message);
    }

    public void warning(String message) {
        log(LoggerLevel.WARNING, message);
    }

    public void error(String message) {
        log(LoggerLevel.ERROR, message);
    }

    public void debug(String format, Object... objects) {
        log(LoggerLevel.DEBUG, format, objects);
    }

    public void info(String format, Object... objects) {
        log(LoggerLevel.INFO, format, objects);
    }

    public void warning(String format, Object... objects) {
        log(LoggerLevel.WARNING, format, objects);
    }

    public void error(String format, Object... objects) {
        log(LoggerLevel.ERROR, format, objects);
    }
}
