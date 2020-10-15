package com.example.task02;

public class DiscountBill extends Bill {
    private final long discount;

    /** @param discount процент скидки в промежутке [0;100] */
    public DiscountBill(long discount){
        if (discount >= 0 && discount <= 100){
            this.discount = discount;
        } else  {
            throw new IllegalArgumentException("аргумент вне промежутка [0;100]");
        }
    }

    /** @return процент скидки */
    public long getDiscount(){
        return discount;
    }

    /** @return разница между суммой и суммой со скидкой */
    public long getDiscountAbsolut(){
        return (long)(super.getPrice() * (discount/100d));
    }


    @Override
    public long getPrice(){
        return (super.getPrice() - getDiscountAbsolut());
    }

}
