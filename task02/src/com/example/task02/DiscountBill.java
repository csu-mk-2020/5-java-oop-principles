package com.example.task02;

/**
 * Класс счёта со скидкой
 */
public class DiscountBill extends Bill {
    private final int discount;

    /**
     * Конструктор объекта счёта со скидкой
     *
     * @param discount скидка на счёт в процентах
     */
    public DiscountBill(int discount) throws IllegalArgumentException {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        this.discount = discount;
    }

    /**
     * Подсчитывает общую сумму покупки
     *
     * @return общую стоимость покупки
     */
    public long getPrice() {
        return super.getPrice() * (100 - discount) / 100;
    }

    /**
     * Подсчитывает размер скидки в процентах
     *
     * @return скидку в процентах
     */
    public int getDiscountPercent() {
        return this.discount;
    }

    /**
     * Подсчитывает размер скидки в условных единицах
     *
     * @return скидка в у.е.
     */
    public long getDiscountAbsolute() {
        return super.getPrice() * this.discount / 100;
    }
}
