package com.example.task04;

import java.text.SimpleDateFormat;
import java.util.*;

public class Logger {
    private final String _name;
    private static final Map<String, Logger> _loggers = new Hashtable<>();
    private LoggerLevel _level = LoggerLevel.DEBUG;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    private final List<MessageHandler> _handlers = new ArrayList<MessageHandler>();

    private void log(LoggerLevel level, String message) throws NullPointerException {
        Objects.requireNonNull(message);
        if (!CanBeLogged(level)) {
            return;
        }
        for(MessageHandler handler: _handlers) {
            handler.handle(
                String.format(
                        "[%s] %s %s - %s%n",
                        level.toString(),
                        dateFormat.format(new Date()),
                        _name,
                        message
                )
            );
        }
    }

    protected boolean CanBeLogged(LoggerLevel level) {
        Objects.requireNonNull(level);
        return _level.ordinal() <= level.ordinal();
    }

    private void log(LoggerLevel level, String format, Object... objects) {
        log(level, String.format(format, objects));
    }

    public Logger(String name, MessageHandler handler) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(handler);
        if (_loggers.containsKey(name)) {
            throw new IllegalArgumentException("Logger with the same name already exists");
        }
        _name = name;
        _handlers.add(handler);
        _loggers.put(name, this);
    }

    public static Logger getLogger(String name) {
        Objects.requireNonNull(name);
        if (!_loggers.containsKey(name)) {
            throw new IllegalArgumentException(String.format("Logger with name %s doesn't exists", name));
        }
        return _loggers.get(name);
    }

    public void addHandler(MessageHandler handler) {
        _handlers.add(handler);
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
