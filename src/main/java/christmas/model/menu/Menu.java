package christmas.model.menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", Type.APPETIZER, 6_000),
    TAPAS("타파스", Type.APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", Type.APPETIZER, 8_000),

    TIBONE_STEAK("티본스테이크", Type.MAIN, 55_000),
    BARBECUE_RIB("바비큐립", Type.MAIN, 54_000),
    SEAFOOD_PASTA("해산물파스타", Type.MAIN, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", Type.MAIN, 25_000),

    CHOCO_CAKE("초코케이크", Type.DESSERT, 15_000),
    ICE_CREAM("아이스크림", Type.DESSERT, 5_000),

    ZERO_COLA("제로콜라", Type.DRINK, 3_000),
    RED_WINE("레드와인", Type.DRINK, 60_000),
    CHAMPAGNE("샴페인", Type.DRINK, 25_000),
    NONE("없음", null, 0);

    private final String name;
    private final Type type;
    private final int price;

    Menu(final String name, final Type type, final int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static Map<String, Integer> getPriceOfMenu() {
        Map<String, Integer> menus = new HashMap<>();
        Arrays.stream(Menu.values())
                .forEach(menu -> {
                    final String name = menu.getName();
                    final int price = menu.getPrice();
                    menus.put(name, price);
                });
        return menus;
    }
}