package com.example.task02;

public class DiscountBill extends Bill {
    long discount = 0;

    DiscountBill(long discount) {
        if (discount <= 100) {
            this.discount = discount;
        }
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
