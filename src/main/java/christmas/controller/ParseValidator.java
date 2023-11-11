package christmas.controller;

import christmas.model.menu.Menu;
import christmas.utils.ErrorConstants;
import java.util.Map;

public class ParseValidator {
    public static void isSmallerThanOne(final int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
        }
    }

    public static void isDuplicateMenu(final Map<String, Integer> menus, final String name) {
        if (menus.containsKey(name)) {
            throw new IllegalArgumentException(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
        }
    }

    public static void isOnlyDrink(final Map<String, Integer> menus) {
        final boolean isNotOnlyDrink = menus.keySet()
                .stream()
                .anyMatch(Menu::getTypeByName);
        if (!isNotOnlyDrink) {
            throw new IllegalArgumentException(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
        }
    }
}
