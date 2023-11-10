package christmas.view;

import christmas.model.menu.Menu;
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
        System.out.println("12월 " + visitDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void orderMenu(final Map<String, Integer> menus) {
        System.out.println("<주문 메뉴>");
        menus.keySet()
                .forEach(menu -> System.out.println(menu + " " + menus.get(menu) + "개"));
    }

    public void totalOrderPriceBeforeDiscount(final int totalPrice) {
        System.out.println("\n<할인 전 총주문 금액>\n" + decimalFormatter(totalPrice));
    }

    public void gift(final Menu gift) {
        String result = "없음";
        if (gift == Menu.CHAMPAGNE) {
            result = gift.getName() + "1개";
        }
        System.out.println("\n<증정 메뉴>\n" + result);
    }

    public void discountList(final int dDayDiscount,
                             final int weekDayDiscount,
                             final int weekendDiscount,
                             final int specialDayDiscount,
                             final int giftDiscount) {
        System.out.println("\n<혜택 내역>");
        if (dDayDiscount != 0) {
            System.out.println("크리스마스 디데이 할인: -" + decimalFormatter(dDayDiscount));
        }
        if (weekDayDiscount != 0) {
            System.out.println("평일 할인: -" + decimalFormatter(weekDayDiscount));
        }
        if (weekendDiscount != 0) {
            System.out.println("주말 할인: -" + decimalFormatter(weekendDiscount));
        }
        if (specialDayDiscount != 0) {
            System.out.println("특별 할인: -" + decimalFormatter(specialDayDiscount));
        }
        if (giftDiscount != 0) {
            System.out.println("증정 이벤트: -" + decimalFormatter(giftDiscount));
        }
        isAllZeroMoney(dDayDiscount, weekDayDiscount, weekendDiscount, specialDayDiscount, giftDiscount);
    }

    public void totalDiscountPrice(final int totalDiscount) {
        System.out.println("\n<총혜택 금액>\n" + decimalFormatter(-1 * totalDiscount));
    }

    public void applyDiscountPrice(final int applyDiscountPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>\n" + decimalFormatter(applyDiscountPrice));
    }

    public void acquiredBadge(final String badgeName) {
        System.out.println("\n<12월 이벤트 배지>\n" + badgeName);
    }

    private void isAllZeroMoney(final int dDayDiscount,
                                final int weekDayDiscount,
                                final int weekendDiscount,
                                final int specialDayDiscount,
                                final int giftDiscount) {
        if (dDayDiscount == 0
                && weekDayDiscount == 0
                && weekendDiscount == 0
                && specialDayDiscount == 0
                && giftDiscount == 0) {
            System.out.println("없음");
        }
    }

    private String decimalFormatter(final int totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(totalPrice) + "원";
    }
}