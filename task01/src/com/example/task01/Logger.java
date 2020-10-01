package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class Logger {
    private final String _name;
    private static final Map<String, Logger> _loggers = new Hashtable<>();
    private LoggerLevel _level = LoggerLevel.DEBUG;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");

    protected boolean CanBeLogged(LoggerLevel level) {
        return _level.ordinal() <= level.ordinal();
    }

    private void log(LoggerLevel level, String message) throws NullPointerException {
        Objects.requireNonNull(message);
        if (!CanBeLogged(level)) {
            return;
        }
        System.out.printf(
                "[%s] %s %s - %s%n",
                level.toString(),
                dateFormat.format(new Date()),
                _name,
                message
        );
    }

    public void log(LoggerLevel level, String format, Object... objects) {
        log(level, String.format(format, objects));
    }

    protected Logger(String name) {
        _name = name;
    }

    public static Logger getLogger(String name) throws NullPointerException  {
        Objects.requireNonNull(name);
        return _loggers.computeIfAbsent(name, Logger::new);
    }

    public String getName() {
        return _name;
    }

    public LoggerLevel getLevel() {
        return _level;
    }

    public void setLevel(LoggerLevel level) {
        _level = level;
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
