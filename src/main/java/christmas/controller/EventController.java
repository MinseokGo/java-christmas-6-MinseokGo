package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Customer;
import christmas.model.Discount;
import christmas.model.Order;
import christmas.model.Parser;
import christmas.model.menu.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventController {
    private int visitDate;
    private Customer customer;
    private Order order;
    private Discount discount;
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
        visitDate = Integer.parseInt(input);
    }

    public void inputOrderMenuAndNumberOfMenu() {
        inputView.askOrderMenuAndNumberOfMenuMessage();
        String input;
        do {
            input = Console.readLine();
        } while (inputValidator.orderMenuAndNumberOfMenu(input));
        final Map<String, Integer> menus = Parser.menu(input);
        order = new Order(menus);
        customer = new Customer(visitDate, order);
    }

    public void orderPreview() {
        outputView.eventPreviewMessage(visitDate);

        final Map<String, Integer> menus = order.getMenus();
        outputView.orderMenu(menus);

        final int totalPrice = order.calculateTotalPrice();
        outputView.totalOrderPriceBeforeDiscount(totalPrice);

        final Menu menu = order.judgeCanGetGiftMenu();
        outputView.gift(menu);
    }

    public void eventBenefitPreview() {
        initDiscount();
        final int totalDiscount = discount.calculateTotalDiscount();
        outputView.totalDiscountPrice(totalDiscount);
    }

    private void initDiscount() {
        final int dDayDiscount = customer.calculateDDayDiscount();
        final int weekDayDiscount = customer.calculateWeekDayDiscount();
        final int weekendDiscount = customer.calculateWeekendDiscount();
        final int specialDayDiscount = customer.calculateSpecialDayDiscount();
        final int giftDiscount = customer.calculateGiftDiscount();
        discount = new Discount(dDayDiscount, weekDayDiscount, weekendDiscount, specialDayDiscount, giftDiscount);
        outputView.discountList(dDayDiscount, weekDayDiscount, weekendDiscount, specialDayDiscount, giftDiscount);
    }
}
