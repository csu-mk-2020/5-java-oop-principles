package com.example.task02;

public class DiscountBill extends Bill {
    private final long discount;

    /**
     * @param discount - скидка в процентах
     */
    DiscountBill(long discount) {
        if (discount < 0 || discount > 100)
            throw new IllegalArgumentException("0 < discount <= 100");
        this.discount = discount;
    }

    public long getPrice() {
        long price = super.getPrice();
        return (long) (price * (1d - discount / 100d));
    }

    public long getAbsDiscount() {
        long price = super.getPrice();
        return (long) (price * discount / 100d);
    }

    public long getDiscountInPercents() {
        return discount;
    }
}
