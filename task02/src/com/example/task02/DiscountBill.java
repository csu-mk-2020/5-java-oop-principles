package com.example.task02;

public class DiscountBill extends Bill {
    private long discount = 0;

    public DiscountBill(long discount) throws  IllegalStateException {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be in [0;100]");
        }
        this.discount = discount;
    }

    public long getDiscountPercents() {
        return discount;
    }

    public long getDiscount() {
        long totalPrice = super.getPrice();
        return totalPrice * discount / 100;
    }

    @Override
    public long getPrice() {
        return super.getPrice() - getDiscount();
    }
}
