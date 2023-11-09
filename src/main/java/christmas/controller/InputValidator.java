package christmas.controller;

import christmas.model.Parser;
import christmas.model.menu.Menu;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        if (1 > number || number > 31) {
            throw new IllegalArgumentException(DATE_NOT_VALID_MESSAGE);
        }
    }

    private void isStringFormat(final String input) {
        if (!input.matches("^([가-힣]+)-(\\d+)(,[가-힣]+-(\\d+))*$")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}