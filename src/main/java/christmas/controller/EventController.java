package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputView;

public class EventController {
    private final InputView inputView;
    private final InputValidator inputValidator;

    public EventController() {
        this.inputView = InputView.getInstance();
        this.inputValidator = InputValidator.getInstance();
    }

    public void plan() {
        inputView.askRestaurantVisitDateMessage();
        String input;
        do {
            input = Console.readLine();
        } while (inputValidator.visitDate(input));
    }
}
