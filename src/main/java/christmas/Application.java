package christmas;

import christmas.controller.EventController;
import christmas.model.Order;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        EventController eventController = new EventController();
        eventController.inputVisitDate();
        eventController.inputOrderMenuAndNumberOfMenu();
        eventController.previewEvent();
    }
}
