package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * Класс логгирования сообщений с различными уровнями важности
 * */
public class Logger {
    public static final HashMap<String,Logger> loggers = new HashMap<>();
    private LoggerLevel level;
    protected final String name;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY.MM.DD hh:mm:ss");

    protected Logger(String name){
        this.name = name;
    }
    /** возвращение имени логгера */
    public String getName(){
        return name;
    }
    /** возвращение логгера с указанным именем */
    public static Logger getLogger(String name){
        Objects.requireNonNull(name, "argument is null");
        return loggers.computeIfAbsent(name,Logger::new);
    }
    /** возвращение действующего уровня важности */
    public LoggerLevel getLevel(){
        return level;
    }
    /** задание уровня важности */
    public void setLevel(LoggerLevel lvl){
        level = Objects.requireNonNull(lvl, "argument is null");
    }


    private void log(LoggerLevel lvl, String msg){
        Objects.requireNonNull(msg, "argument is null");
        if (lvl.ordinal() >= level.ordinal()) {
            System.out.println("[" + lvl.toString() + "] " + dateFormat.format(new Date()) + " " + name + " - " + msg);
        }
    }
    private void log(LoggerLevel lvl,String format, Object... objects){
        log(lvl,String.format(format,objects));
    }

    /** генерация сообщения DEBUG с приставкой msg */
    public void debug(String msg){
        log(LoggerLevel.DEBUG, msg);
    }
    /** генерация сообщения INFO с приставкой msg */
    public void info(String msg){
        log(LoggerLevel.INFO, msg);
    }
    /** генерация сообщения WARNING с приставкой msg */
    public void warning(String msg){
        log(LoggerLevel.WARNING, msg);
    }
    /** генерация сообщения ERROR с приставкой mcg */
    public void error(String msg){
        log(LoggerLevel.ERROR, msg);
    }

    /** генерация сообщения DEBUG с шаблоном */
    public void debug(String format, Object... objects){
        log(LoggerLevel.DEBUG, format, objects);
    }
    /** генерация сообщения INFO с шаблоном */
    public void info(String format, Object... objects){
        log(LoggerLevel.INFO, format, objects);
    }
    /** генерация сообщения WARNING с шаблоном */
    public void warning(String format, Object... objects){
        log(LoggerLevel.WARNING, format, objects);
    }
    /** генерация сообщения ERROR с шаблоном */
    public void error(String format, Object... objects){
        log(LoggerLevel.ERROR, format, objects);
    }
}
