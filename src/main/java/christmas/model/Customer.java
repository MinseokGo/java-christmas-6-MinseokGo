package christmas.model;

import christmas.model.menu.Menu;
import christmas.model.menu.Type;
import christmas.utils.DateUtils;
import java.util.List;
import java.util.Map;

public class Customer {
    private final int visitDate;
    private final Order order;
    private Discount discount;

    public Customer(final int visitDate, final Order order) {
        this.visitDate = visitDate;
        this.order = order;
    }

    public int calculateDDayDiscount() {
        if (visitDate <= DateUtils.CHRISTMAS_DAY) {
            return (visitDate - 1) * 100 + 1000;
        }
        return 0;
    }

    public int calculateWeekendDiscount() {
        final int day = DateUtils.getDayNumber(visitDate);
        final Map<String, Integer> menus = order.getMenus();
        if (day == DateUtils.FRIDAY || day == DateUtils.SATURDAY) {
            return calculateWeeklyDiscount(menus, Type.MAIN);
        }
        return 0;
    }

    public int calculateWeekDayDiscount() {
        final int day = DateUtils.getDayNumber(visitDate);
        final Map<String, Integer> menus = order.getMenus();
        if (!(day == DateUtils.FRIDAY) && !(day == DateUtils.SATURDAY)) {
            return calculateWeeklyDiscount(menus, Type.DESSERT);
        }
        return 0;
    }

    public int calculateSpecialDayDiscount() {
        final int day = DateUtils.getDayNumber(visitDate);
        if (day == DateUtils.SUNDAY || visitDate == DateUtils.CHRISTMAS_DAY) {
            return 1000;
        }
        return 0;
    }

    public int calculateGiftDiscount() {
        return order.getGiftPrice();
    }

    private int calculateWeeklyDiscount(final Map<String, Integer> menus, final Type type) {
        final List<String> menuNamesByType = Menu.getMenuByType(type);
        final int count = Menu.countMenu(menus, menuNamesByType);
        return count * DateUtils.THIS_YEAR;
    }
}