package christmas.view;

import java.util.Map;

public class OutputView {
    private static final OutputView outputView = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return outputView;
    }

    public void eventPreviewMessage(final int visitDate) {
        System.out.println("12월 " + visitDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void orderMenu(final Map<String, Integer> menus) {
        System.out.println("<주문 메뉴>");
        menus.keySet()
                .forEach(menu -> System.out.println(menu + " " + menus.get(menu) + "개"));
    }
}
