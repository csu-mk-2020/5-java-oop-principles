package com.example.task02;

public class DiscountBill extends Bill {
    private final long discount;

    /**
     * @param discount - скидка в процентах
     */
    public DiscountBill(long discount) {
        if (discount < 0 || discount > 100)
            throw new IllegalArgumentException("0 < discount <= 100");
        this.discount = discount;
    }

    /**
     * @return цена с учётом скидки
     */
    public long getPrice() {
        long price = super.getPrice();
        return (long) (price - price * discount / 100d);
    }

    /**
     * @return разница между суммой и суммой со скидкой
     */
    public long getAbsDiscount() {
        return (long) (super.getPrice() * discount / 100d);
    }

    /**
     * @return размера скидки (в процентах)
     */
    public long getDiscountInPercents() {
        return discount;
    }
}
