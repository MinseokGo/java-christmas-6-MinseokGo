package christmas.model;

public class Discount {
    public static int CHRISTMAS_DISCOUNT_PRICE = 100;
    public static int INIT_DISCOUNT_PRICE = 1000;
    public static int NONE_DISCOUNT_PRICE = 0;

    private final int dDay;
    private final int weekDay;
    private final int weekend;
    private final int specialDay;
    private final int gift;

    public Discount(final int dDay, final int weekDay, final int weekend, final int specialDay, final int gift) {
        this.dDay = dDay;
        this.weekDay = weekDay;
        this.weekend = weekend;
        this.specialDay = specialDay;
        this.gift = gift;
    }

    public int calculateTotalDiscount() {
        return dDay + weekDay + weekend + specialDay + gift;
    }

    public int calculateRealDiscount() {
        return dDay + weekDay + weekend + specialDay;
    }
}
