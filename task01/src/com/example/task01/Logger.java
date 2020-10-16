package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class Logger {
    private final String name;
    private static final Map<String, Logger> loggers = new Hashtable<>();
    private LoggerLevel level = LoggerLevel.DEBUG;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");

    protected boolean canBeLogged(LoggerLevel level) {
        return this.level.ordinal() <= level.ordinal();
    }

    private void log(LoggerLevel level, String message) throws NullPointerException {
        Objects.requireNonNull(message);
        if (!canBeLogged(level)) {
            return;
        }
        System.out.printf(
                "[%s] %s %s - %s%n",
                level.toString(),
                dateFormat.format(new Date()),
                name,
                message
        );
    }

    public void log(LoggerLevel level, String format, Object... objects) {
        log(level, String.format(format, objects));
    }

    protected Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) throws NullPointerException  {
        Objects.requireNonNull(name);
        return loggers.computeIfAbsent(name, Logger::new);
    }

    public String getName() {
        return name;
    }

    public LoggerLevel getLevel() {
        return level;
    }

    public void setLevel(LoggerLevel level) {
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
