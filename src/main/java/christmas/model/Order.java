package christmas.model;

import java.util.Map;

public class Order {
    private final Map<String, Integer> menus;

    public Order(final Map<String, Integer> menus) {
        this.menus = menus;
    }

    public Map<String, Integer> getMenus() {
        return menus;
    }
}
