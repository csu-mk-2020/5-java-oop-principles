package com.example.task01;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Logger {
    private LogLevels thisLevel;
    private String name;
    private static int maxLevel = 0;
    private static List<Logger> loggerList = new ArrayList<>();

    protected Logger(String Name) {
        name = Name;
        loggerList.add(this);
    }

    public static Logger getLogger(String Name) {
        for (Logger e : loggerList) {
            if (e.name.equals(Name)) {
                return e;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getName() {
        return this.name;
    }

    public LogLevels getLevel() {
        return thisLevel;
    }

    public void setLevel(LogLevels lvl) {
        thisLevel = lvl;

        switch (lvl) {
            case DEBUG:
                maxLevel = 1;
                break;
            case INFO:
                maxLevel = 2;
                break;
            case ERROR:
                maxLevel = 3;
                break;
            case WARNING:
                maxLevel = 4;
                break;
            default:
                break;
        }
    }

    public void log(LogLevels level, String message) {
        log(level, message, (Object) null);
    }

    public void log(LogLevels level, String message, Object... obj) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        if (level.ordinal() >= this.thisLevel.ordinal()) {
            System.err.println("[" + this.thisLevel.toString() + "]" + sdfDate.format(new Date()) +
                    this.name + " - " + message);
        }
    }

    public void debug(String msg) {
        this.log(LogLevels.DEBUG, msg);
    }

    public void debug(String template, Object... obj) {
        this.log(LogLevels.DEBUG, template, obj);
    }

    public void info(String msg) {
        this.log(LogLevels.INFO, msg);
    }

    public void info(String template, Object... obj) {
        this.log(LogLevels.INFO, template, obj);
    }

    public void warning(String msg) {
        this.log(LogLevels.WARNING, msg);
    }

    public void warning(String template, Object... obj) {
        this.log(LogLevels.WARNING, template, obj);
    }

    public void error(String msg) {
        this.log(LogLevels.ERROR, msg);
    }

    public void error(String template, Object... obj) {
        this.log(LogLevels.ERROR, template, obj);
    }
}
