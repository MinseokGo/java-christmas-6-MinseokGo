package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputView;

public class EventController {
    private final InputView inputView;

    public EventController() {
        this.inputView = InputView.getInstance();
    }

    public void plan() {
        inputView.askRestaurantVisitDateMessage();
        String input;
        do {
            input = Console.readLine();
        } while (true);
    }
}
