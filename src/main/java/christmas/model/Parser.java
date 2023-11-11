package christmas.model;

import christmas.controller.ParseValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static Map<String, Integer> menu(final String input) {
        Map<String, Integer> menus = new HashMap<>();
        List<String> splitInput = List.of(input.split(","));
        splitInput.stream()
                .map(item -> item.split("-"))
                .forEach(menu -> {
                    final String name = menu[0];
                    final int quantity = Integer.parseInt(menu[1]);
                    ParseValidator.isSmallerThanOne(quantity);
                    ParseValidator.isDuplicateMenu(menus, name);
                    menus.put(name, quantity);
                });
        ParseValidator.isOnlyDrink(menus);
        return menus;
    }
}