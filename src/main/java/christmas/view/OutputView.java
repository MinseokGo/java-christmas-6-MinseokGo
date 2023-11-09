package christmas.view;

import java.text.DecimalFormat;
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

    public void totalOrderPriceBeforeDiscount(final int totalPrice) {
        System.out.println("<할인 전 총주문 금액>\n"
                + decimalFormatter(totalPrice));
    }

    private String decimalFormatter(final int totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(totalPrice) + "원";
    }
}
