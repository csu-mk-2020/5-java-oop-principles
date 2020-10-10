package com.example.task03;

public class Minutes implements TimeUnit {

    public final long _amount;

    public Minutes(long amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Minutes cannot be below zero");
        }
        _amount = amount;
    }

    @Override
    public long toMillis() {
        return toSeconds() * 1000;
    }

    @Override
    public long toSeconds() {
        return _amount * 60;
    }

    @Override
    public long toMinutes() {
        return _amount;
    }

    @Override
    public long toHours() {
        return Math.round(_amount / 60d);
    }
}
