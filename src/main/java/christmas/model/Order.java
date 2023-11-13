package christmas.model;

import christmas.model.menu.Menu;
import java.util.Map;

public class Order {
    public static int MIN_PRICE = 10_000;
    public static int MIN_NUMBER_OF_MENU = 1;
    public static int MAX_NUMBER_OF_MENU = 20;
    private static int CHECK_GIFT_PRICE = 120_000;

    private final Map<String, Integer> menus;
    private int totalPrice;
    private int applyDiscountPrice;
    private Menu gift;

    public Order(final Map<String, Integer> menus) {
        this.menus = menus;
        this.gift = Menu.NONE;
    }

    public Map<String, Integer> getMenus() {
        return menus;
    }

    public int getTotalPrice() {
        return totalPrice;
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
        if (totalPrice >= CHECK_GIFT_PRICE) {
            gift = Menu.CHAMPAGNE;
            return gift;
        }
        return Menu.NONE;
    }

    public int getGiftPrice() {
        if (gift != Menu.NONE) {
            return gift.getPrice();
        }
        return Discount.NONE_DISCOUNT_PRICE;
    }

    public int calculateApplyDiscountPrice(final int discount) {
        applyDiscountPrice = totalPrice - discount;
        return applyDiscountPrice;
    }
}