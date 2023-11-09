package christmas.model;

import christmas.model.menu.Menu;
import christmas.model.menu.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
        if (visitDate <= 25) {
            return (visitDate - 1) * 100 + 1000;
        }
        return 0;
    }

    public int calculateWeekendDiscount() {
        final int dayNumber = getDayNumber();
        final Map<String, Integer> menus = order.getMenus();
        if (dayNumber == 5 || dayNumber == 6) {
            return calculateWeeklyDiscount(menus, Type.MAIN);
        }
        return 0;
    }

    public int calculateWeekDayDiscount() {
        final int dayNumber = getDayNumber();
        final Map<String, Integer> menus = order.getMenus();
        if (!(dayNumber == 5) && !(dayNumber == 6)) {
            return calculateWeeklyDiscount(menus, Type.DESSERT);
        }
        return 0;
    }

    public int calculateSpecialDayDiscount() {
        final int dayNumber = getDayNumber();
        if (dayNumber == 7 || visitDate == 25) {
            return 1000;
        }
        return 0;
    }

    public int calculateGiftDiscount() {
        return order.getGiftPrice();
    }

    private int calculateWeeklyDiscount(final Map<String, Integer> menus, final Type type) {
        final List<String> menuNamesByType = Menu.getMenuByType(type);
        final int count = countMenu(menus, menuNamesByType);
        return count * 2023;
    }

    private int countMenu(final Map<String, Integer> menus, final List<String> menuNamesByType) {
        List<String> specificMenus = menus.keySet()
                .stream()
                .filter(menuNamesByType::contains)
                .toList();
        int count = 0;
        for (String menu : menus.keySet()) {
            if (specificMenus.contains(menu)) {
                count += menus.get(menu);
            }
        }
        return count;
    }

    private int getDayNumber() {
        final LocalDate date = LocalDate.of(2023, 12, visitDate);
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue();
    }
}