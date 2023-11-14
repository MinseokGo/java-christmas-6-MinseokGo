package christmas.model;

import christmas.model.menu.Menu;
import christmas.model.menu.Type;
import christmas.utils.DateUtils;
import java.util.List;
import java.util.Map;

public class Discount {
    public static int CHRISTMAS_DISCOUNT_PRICE = 100;
    public static int INIT_DISCOUNT_PRICE = 1000;
    public static int NONE_DISCOUNT_PRICE = 0;

    private int dDay;
    private int weekDay;
    private int weekend;
    private int specialDay;
    private int gift;

    public int calculateTotalDiscount() {
        return dDay + weekDay + weekend + specialDay + gift;
    }

    public int calculateRealDiscount() {
        return dDay + weekDay + weekend + specialDay;
    }



    public int calculateDDayDiscount(final int visitDate) {
        if (visitDate <= DateUtils.CHRISTMAS_DAY) {
            dDay = (visitDate - DateUtils.EVENT_START_DATE) * Discount.CHRISTMAS_DISCOUNT_PRICE
                    + Discount.INIT_DISCOUNT_PRICE;
            return dDay;
        }
        return Discount.NONE_DISCOUNT_PRICE;
    }

    public int calculateWeekendDiscount(final int visitDate, final Order order) {
        final int day = DateUtils.getDayNumber(visitDate);
        final Map<String, Integer> menus = order.getMenus();
        if (day == DateUtils.FRIDAY || day == DateUtils.SATURDAY) {
            weekend = calculateWeeklyDiscount(menus, Type.MAIN);
            return weekend;
        }
        return Discount.NONE_DISCOUNT_PRICE;
    }

    public int calculateWeekDayDiscount(final int visitDate, final Order order) {
        final int day = DateUtils.getDayNumber(visitDate);
        final Map<String, Integer> menus = order.getMenus();
        if (!(day == DateUtils.FRIDAY) && !(day == DateUtils.SATURDAY)) {
            weekDay = calculateWeeklyDiscount(menus, Type.DESSERT);
            return weekDay;
        }
        return Discount.NONE_DISCOUNT_PRICE;
    }

    public int calculateSpecialDayDiscount(final int visitDate) {
        final int day = DateUtils.getDayNumber(visitDate);
        if (day == DateUtils.SUNDAY || visitDate == DateUtils.CHRISTMAS_DAY) {
            specialDay = Discount.INIT_DISCOUNT_PRICE;
            return specialDay;
        }
        return Discount.NONE_DISCOUNT_PRICE;
    }

    public int calculateGiftDiscount(final Order order) {
        gift = order.getGiftPrice();
        return gift;
    }

    private int calculateWeeklyDiscount(final Map<String, Integer> menus, final Type type) {
        final List<String> menuNamesByType = Menu.getMenuByType(type);
        final int count = Menu.countMenu(menus, menuNamesByType);
        return count * DateUtils.THIS_YEAR;
    }
}
