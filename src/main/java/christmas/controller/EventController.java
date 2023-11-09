package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Order;
import christmas.model.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventController {
    private Order order;
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator inputValidator;

    public EventController() {
        this.inputView = InputView.getInstance();
        this.outputView = OutputView.getInstance();
        this.inputValidator = InputValidator.getInstance();
    }

    public void inputVisitDate() {
        inputView.askRestaurantVisitDateMessage();
        String input;
        do {
            input = Console.readLine();
        } while (inputValidator.visitDate(input));
    }

    public void inputOrderMenuAndNumberOfMenu() {
        inputView.askOrderMenuAndNumberOfMenuMessage();
        String input;
        do {
            input = Console.readLine();
        } while (inputValidator.orderMenuAndNumberOfMenu(input));
        final Map<String, Integer> menus = Parser.menu(input);
        this.order = new Order(menus);
    }

    public void previewEvent() {
        outputView.eventPreviewMessage(23);

        final Map<String, Integer> menus = order.getMenus();
        outputView.orderMenu(menus);
    }
}
