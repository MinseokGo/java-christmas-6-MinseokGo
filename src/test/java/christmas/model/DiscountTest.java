package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {
    private Order order;
    private final Discount discount = new Discount();

    @BeforeEach
    void setUp() {
        final Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 10);
        menus.put("초코케이크", 10);
        order = new Order(menus);
        order.calculateTotalPrice();
        order.judgeCanGetGiftMenu();
    }

    @DisplayName("주문의 총 금액을 계산한다.")
    @Test
    void calculateTotalDiscount() {
        final int totalPrice = order.getTotalPrice();
        final int expectTotalPrice = 700_000;

        assertThat(totalPrice).isEqualTo(expectTotalPrice);
    }

    @DisplayName("실제 할인 금액을 계산한다.")
    @Test
    void calculateRealDiscount() {
        discount.calculateDDayDiscount(2);
        discount.calculateWeekendDiscount(2, order);
        discount.calculateWeekDayDiscount(3, order);
        discount.calculateSpecialDayDiscount(3);
        discount.calculateGiftDiscount(order);

        final int realDiscountPrice = discount.calculateRealDiscount();

        assertThat(realDiscountPrice).isEqualTo(42560);
    }

    @DisplayName("크리스마스 디데잍 할인 금액을 계산한다.")
    @Test
    void calculateDDayDiscount() {
        final int dDayDiscountPrice = discount.calculateDDayDiscount(2);

        assertThat(dDayDiscountPrice).isEqualTo(1100);
    }

    @DisplayName("주말 할인을 계산한다.")
    @Test
    void calculateWeekendDiscount() {
        final int weekendDiscountPrice = discount.calculateWeekendDiscount(2, order);

        assertThat(weekendDiscountPrice).isEqualTo(20230);
    }

    @DisplayName("평일 할인을 계산한다.")
    @Test
    void calculateWeekDayDiscount() {
        final int weekDayDiscountPrice = discount.calculateWeekDayDiscount(3, order);

        assertThat(weekDayDiscountPrice).isEqualTo(20230);
    }

    @DisplayName("특별 할인을 계산한다.")
    @Test
    void calculateSpecialDayDiscount() {
        final int specialDayDiscountPrice = discount.calculateSpecialDayDiscount(3);

        assertThat(specialDayDiscountPrice).isEqualTo(1000);
    }

    @DisplayName("증정 이벤트 할인 금액을 계산한다.")
    @Test
    void calculateGiftDiscount() {
        final int giftDiscountPrice = discount.calculateGiftDiscount(order);

        assertThat(giftDiscountPrice).isEqualTo(25000);
    }
}