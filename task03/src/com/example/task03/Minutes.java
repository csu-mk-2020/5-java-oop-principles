package com.example.task03;

public class Minutes implements TimeUnit {

    private final long amount;

    public Minutes(long amount) {
        if (amount < 0)
            throw new IllegalArgumentException("amount must be >= 0");
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return this.amount * 60000;
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