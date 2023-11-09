package christmas.model;

import christmas.model.menu.Menu;
import java.util.Map;

public class Order {
    private final Map<String, Integer> menus;
    private int totalPrice;
    private Menu gift;

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
                    totalPrice += prices.get(menu) * menus.get(menu);
                });
        return totalPrice;
    }

    public Menu judgeCanGetGiftMenu() {
        if (totalPrice >= 120000) {
            gift = Menu.CHAMPAGNE;
            return gift;
        }
        return Menu.NONE;
    }
}
