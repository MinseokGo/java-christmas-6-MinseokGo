package christmas.controller;

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
}
