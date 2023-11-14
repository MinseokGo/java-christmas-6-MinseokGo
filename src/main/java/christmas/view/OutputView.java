package christmas.view;

import christmas.model.Discount;
import christmas.model.menu.Menu;
import christmas.utils.DateUtils;
import christmas.utils.ErrorConstants;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

public class OutputView {
    private static final int INIT_VALUE = 0;
    private static final int DISCOUNT_PREFIX_NUMBER = -1;
    private static final String DECIMAL_FORMAT = "###,###";
    private static final Map<String, Integer> DISCOUNT_MAP = Map.of(
            "크리스마스 디데이 할인", 0,
            "평일 할인", 1,
            "주말 할인", 2,
            "특별 할인", 3,
            "증정 이벤트", 4
    );

    private static final OutputView outputView = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return outputView;
    }

    public void eventPreviewMessage(final int visitDate) {
        System.out.println(DateUtils.THIS_MONTH + "월 " + visitDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
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
            result = gift.getName() + " 1개";
        }
        System.out.println("\n<증정 메뉴>\n" + result);
    }

    public void discountList(final int... discounts) {
        System.out.println("\n<혜택 내역>");
        IntStream.range(INIT_VALUE, discounts.length)
                .filter(i -> discounts[i] != Discount.NONE_DISCOUNT_PRICE)
                .forEach(i -> {
                    final String benefit = getKeyByValue(i);
                    System.out.println(benefit + ": " + decimalFormatter(DISCOUNT_PREFIX_NUMBER * discounts[i]));
                });
        isAllZeroMoney(discounts);
    }

    public void totalDiscountPrice(final int totalDiscount) {
        System.out.println("\n<총혜택 금액>\n" + decimalFormatter(DISCOUNT_PREFIX_NUMBER * totalDiscount));
    }

    public void applyDiscountPrice(final int applyDiscountPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>\n" + decimalFormatter(applyDiscountPrice));
    }

    public void acquiredBadge(final String badgeName) {
        System.out.println("\n<" + DateUtils.THIS_MONTH + "월 이벤트 배지>\n" + badgeName);
    }

    private String getKeyByValue(final int value) {
        for (String key : DISCOUNT_MAP.keySet()) {
            if (DISCOUNT_MAP.get(key).equals(value)) {
                return key;
            }
        }
        throw new IllegalArgumentException(ErrorConstants.EVENT_NOT_VALID_MESSAGE);
    }

    private void isAllZeroMoney(final int... discounts) {
        final boolean isZero = Arrays.stream(discounts)
                .allMatch(discount -> discount == Discount.NONE_DISCOUNT_PRICE);
        if (isZero) {
            System.out.println("없음");
        }
    }

    private String decimalFormatter(final int totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
        return decimalFormat.format(totalPrice) + "원";
    }
}