package com.example.task02;



public class DiscountBill extends Bill {
    private double discount;

    public DiscountBill(double discount) {
        if (discount >= 100 || discount < 0) {
            throw new IllegalArgumentException("Error discount");
        }
        this.discount = discount;
    }

    public long getPrice() {
        return super.getPrice() - getDiscount();
    }

    public long getDiscount() {
        return (long)(super.getPrice() * discount / 100);
    }

    public double getAbsDiscount() {
        return super.getPrice() - getPrice();
    }
}