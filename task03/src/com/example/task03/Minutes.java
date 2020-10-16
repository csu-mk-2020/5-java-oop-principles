package com.example.task03;

public class Minutes implements TimeUnit {

    public final long amount;

    public Minutes(long amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Minutes cannot be below zero");
        }
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return toSeconds() * 1000;
    }

    @Override
    public long toSeconds() {
        return this.amount * 60;
    }

    @Override
    public long toMinutes() {
        return this.amount;
    }

    @Override
    public long toHours() {
        return Math.round(this.amount / 60d);
    }
}
