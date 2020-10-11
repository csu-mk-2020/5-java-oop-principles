package com.example.task03;

public class Hours implements TimeUnit {
    private final long amount;

    public Hours(long amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return this.amount * 60 * 60 * 1000;
    }

    @Override
    public long toSeconds() {
        return this.amount * 60 * 60;
    }

    @Override
    public long toMinutes() {
        return this.amount * 60;
    }

    @Override
    public long toHours() {
        return this.amount;
    }
}
