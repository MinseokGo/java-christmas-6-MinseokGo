package christmas.view;

import christmas.utils.DateUtils;

public class InputView {
    private static final InputView inputView = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return inputView;
    }

    public void askRestaurantVisitDateMessage() {
        System.out.println("안녕하세요! 우테코 식당 " + DateUtils.THIS_MONTH + "월 이벤트 플래너입니다.");
        System.out.println(DateUtils.THIS_MONTH + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void askOrderMenuAndNumberOfMenuMessage() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }
}