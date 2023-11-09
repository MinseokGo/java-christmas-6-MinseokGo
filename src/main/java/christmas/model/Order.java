package christmas.model;

import christmas.model.menu.Menu;
import java.util.Map;

public class Order {
    private final Map<String, Integer> menus;
    private int totalPrice;

    public Order(final Map<String, Integer> menus) {
        this.menus = menus;
    }

    public Map<String, Integer> getMenus() {
        return menus;
    }

    public int calculateTotalPrice() {
        Map<String, Integer> prices = Menu.getPriceOfMenu();
        menus.keySet()
                .forEach(menu -> {
                    totalPrice += prices.get(menu);
                });
        return totalPrice;
    }
}
