package christmas;

import christmas.controller.EventController;
import christmas.model.Discount;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        EventController eventController = new EventController(new Discount());
        eventController.inputVisitDate();
        eventController.inputOrderMenuAndNumberOfMenu();
        eventController.orderPreview();
        eventController.eventBenefitPreview();
    }
}
