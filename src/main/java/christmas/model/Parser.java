package christmas.model;

import christmas.controller.ParseValidator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Integer> menu(final String input) {
        final Map<String, Integer> menus = new HashMap<>();
        Arrays.stream(input.split(","))
                .map(item -> item.split("-"))
                .forEach(menu -> processMenuEntry(menu, menus));
        ParseValidator.isOnlyDrink(menus);
        return menus;
    }

    private static void processMenuEntry(String[] menu, Map<String, Integer> menus) {
        final String name = menu[0];
        final int quantity = Integer.parseInt(menu[1]);
        ParseValidator.isSmallerThanOne(quantity);
        ParseValidator.isDuplicateMenu(menus, name);
        menus.put(name, quantity);
    }
}