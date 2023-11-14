package christmas.model;

import christmas.controller.ParseValidator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final String INPUT_SPLIT_REGEX = ",";
    private static final String MENU_INPUT_SPLIT_REGEX = "-";

    public static Map<String, Integer> menu(final String input) {
        final Map<String, Integer> menus = new HashMap<>();
        Arrays.stream(input.split(INPUT_SPLIT_REGEX))
                .map(item -> item.split(MENU_INPUT_SPLIT_REGEX))
                .forEach(menu -> processMenuEntry(menu, menus));
        ParseValidator.isExceedNumberOfMenu(menus);
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