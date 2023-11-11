package christmas.controller;

import christmas.model.Parser;
import christmas.model.menu.Menu;
import christmas.utils.DateUtils;
import christmas.utils.ErrorConstants;
import java.util.Map;
import java.util.Set;

public class InputValidator {
    public final String DATE_NOT_VALID_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private static final InputValidator inputValidator = new InputValidator();

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        return inputValidator;
    }

    public boolean visitDate(final String input) {
        try {
            isNumberFormat(input);
            isNumberBoundary(input);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    public boolean orderMenuAndNumberOfMenu(final String input) {
        try {
            isStringFormat(input);
            final Map<String, Integer> menus = Parser.menu(input);
            isExistsMenu(menus);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private void isNumberFormat(final String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException(DATE_NOT_VALID_MESSAGE);
        }
    }

    private void isNumberBoundary(final String input) {
        final int number = Integer.parseInt(input);
        if (number < DateUtils.EVENT_START_DATE || number > DateUtils.EVENT_END_DATE) {
            throw new IllegalArgumentException(DATE_NOT_VALID_MESSAGE);
        }
    }

    private void isStringFormat(final String input) {
        if (!input.matches("^([가-힣]+)-(\\d+)(,[가-힣]+-(\\d+))*$")) {
            throw new IllegalArgumentException(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
        }
    }

    private void isExistsMenu(final Map<String, Integer> menus) {
        Set<String> existsMenu = Menu.getAllMenuName();
        menus.keySet().stream()
                .filter(menu -> !existsMenu.contains(menu))
                .findAny()
                .ifPresent(menu -> {
                    System.out.println("menu = " + menu);
                    throw new IllegalArgumentException("sex" + ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
                });
    }
}