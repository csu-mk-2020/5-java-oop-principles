package com.example.task03;

/**
 * Класс, в котором собраны методы для работы с {@link TimeUnit}
 */
public class TimeUnitUtils {

    /**
     * Конвертирует интервал в интервал в миллисекундах
     *
     * @param unit произвольный интервал
     * @return интервал в миллисекундах
     */
    public static Milliseconds toMillis(TimeUnit unit) {
        return new Milliseconds(unit.toMillis());
    }

    /**
     * Конвертирует интервал в интервал в секундах
     *
     * @param unit произвольный интервал
     * @return интервал в секундах
     */
    public static Seconds toSeconds(TimeUnit unit) {
        return new Seconds(unit.toSeconds());
    }

    /**
     * Конвертирует интервал в миллисекундах в минутах
     *
     * @param unit произвольный интервал
     * @return интервал в минутах
     */
    public static Minutes toMinutes(TimeUnit unit) {
        return new Minutes(unit.toMinutes());
    }

    /**
     * Конвертирует интервал в интервал в часах
     *
     * @param unit произвольный интервал
     * @return интервал в часах
     */
    public static Hours toHours(TimeUnit unit) {
        return new Hours(unit.toHours());
    }

}