package com.example.task03;

public class Minutes implements TimeUnit {
    public final long amount;

    public Minutes(long amount) throws IllegalArgumentException {
        /*
        // TODO: реализовать
        throw new UnsupportedOperationException();
        */
        if (amount < 0) {
            throw new IllegalArgumentException("аргумент не может быть отрицательным");
        }
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        /*
        // TODO: реализовать
        throw new UnsupportedOperationException();
        */
        return toSeconds() * 1000;
    }

    @Override
    public long toSeconds() {
        /*
        // TODO: реализовать
        throw new UnsupportedOperationException();
        */
        return amount * 60;
    }

    @Override
    public long toMinutes() {
        /*
        // TODO: реализовать
        throw new UnsupportedOperationException();
        */
        return amount;
    }

    @Override
    public long toHours() {
        return Math.round(amount / 60d);
    }
}
