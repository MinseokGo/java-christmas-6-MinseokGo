package christmas.model;

public class Discount {
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
