package com.example.task03;

/**
 * Интервал в секундах
 */
public class Seconds implements TimeUnit {

    private final long amount;

    public Seconds(long amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return this.amount * 1000;
    }

    @Override
    public long toSeconds() {
        return this.amount;
    }

    @Override
    public long toMinutes() {
        return Math.round(this.amount / 60d);
    }

    @Override
    public long toHours() {
        return Math.round(this.amount / 60d / 60d);
    }
}
