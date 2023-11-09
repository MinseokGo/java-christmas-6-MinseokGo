package christmas.model;

import java.util.Arrays;
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
                    if (quantity < 1) {
                        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }
                    if (menus.containsKey(name)) {
                        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }
                    menus.put(name, quantity);
                });
        return menus;
    }
}