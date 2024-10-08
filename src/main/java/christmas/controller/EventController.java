package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.Order;
import christmas.model.Parser;
import christmas.model.menu.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventController {
    private int visitDate;
    private Order order;
    private final Discount discount;
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator inputValidator;

    public EventController(final Discount discount) {
        this.inputView = InputView.getInstance();
        this.outputView = OutputView.getInstance();
        this.inputValidator = InputValidator.getInstance();
        this.discount = discount;
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

        final int realDiscountPrice = discount.calculateRealDiscount();
        final int applyDiscountPrice = order.calculateApplyDiscountPrice(realDiscountPrice);
        outputView.applyDiscountPrice(applyDiscountPrice);

        final String badgeName = Badge.getBadge(totalDiscount).getName();
        outputView.acquiredBadge(badgeName);
    }

    private void initDiscount() {
        final int totalPrice = order.getTotalPrice();
        if (totalPrice <= Order.MIN_PRICE) {
            outputView.discountList(Discount.NONE_DISCOUNT_PRICE, Discount.NONE_DISCOUNT_PRICE,
                    Discount.NONE_DISCOUNT_PRICE, Discount.NONE_DISCOUNT_PRICE, Discount.NONE_DISCOUNT_PRICE);
            return;
        }
        initDiscountCanGetEvent();
    }

    private void initDiscountCanGetEvent() {
        final int dDayDiscount = discount.calculateDDayDiscount(visitDate);
        final int weekDayDiscount = discount.calculateWeekDayDiscount(visitDate, order);
        final int weekendDiscount = discount.calculateWeekendDiscount(visitDate, order);
        final int specialDayDiscount = discount.calculateSpecialDayDiscount(visitDate);
        final int giftDiscount = discount.calculateGiftDiscount(order);
        outputView.discountList(dDayDiscount, weekDayDiscount, weekendDiscount, specialDayDiscount, giftDiscount);
    }
}