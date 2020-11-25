package com.example.task03;

public class Hours implements TimeUnit {
    private final long amount;

    public Hours(long amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("аргумент не может быть отрицательным");
        }
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return toSeconds() * 1000;
    }

    @Override
    public long toSeconds() {
        return toMinutes() * 60;
    }

    @Override
    public long toMinutes() {
        return amount * 60;
    }

    @Override
    public long toHours() {
        return amount;
    }
}
