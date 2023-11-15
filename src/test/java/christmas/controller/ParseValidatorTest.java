package christmas.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.utils.ErrorConstants;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParseValidatorTest extends NsTest {
    @DisplayName("주문 개수는 1개 이상이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void isSmallerThanOne(final int quantity) {
        ParseValidator.isSmallerThanOne(quantity);
        assertThat(output()).contains("");
    }

    @DisplayName("실패 : 주문 개수는 1개 미만이다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, -14})
    void isNotSmallerThanOne(final int quantity) {
        assertThatThrownBy(() -> ParseValidator.isSmallerThanOne(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
    }

    @DisplayName("메뉴는 중복 입력되지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스", "제로콜라"})
    void isNotDuplicateMenu(final String name) {
        final Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 1);
        ParseValidator.isDuplicateMenu(menus, name);

        assertThat(output()).contains("");
    }

    @DisplayName("실패 : 메뉴가 중복된다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "제로콜라"})
    void isDuplicateMenu(final String name) {
        final Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 1);
        menus.put("제로콜라", 1);

        assertThatThrownBy(() -> ParseValidator.isDuplicateMenu(menus, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
    }

    @DisplayName("메뉴는 20개 이상 주문할 수 없다.")
    @Test
    void isNotExceedNumberOfMenu() {
        final Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 1);
        menus.put("제로콜라", 1);
        ParseValidator.isExceedNumberOfMenu(menus);

        assertThat(output()).contains("");
    }

    @DisplayName("실패 : 메뉴는 20개 이상 주문된다.")
    @Test
    void isExceedNumberOfMenu() {
        final Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 21);
        menus.put("제로콜라", 1);

        assertThatThrownBy(() -> ParseValidator.isExceedNumberOfMenu(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
    }

    @DisplayName("주문은 음료만 주문할 수 없다.")
    @Test
    void isNotOnlyDrink() {
        final Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 1);
        menus.put("제로콜라", 1);
        ParseValidator.isOnlyDrink(menus);

        assertThat(output()).contains("");
    }

    @DisplayName("실패 : 주문을 음료만 한다.")
    @Test
    void isOnlyDrink() {
        final Map<String, Integer> menus = new HashMap<>();
        menus.put("제로콜라", 1);

        assertThatThrownBy(() -> ParseValidator.isOnlyDrink(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
    }

    @Override
    protected void runMain() {
    }
}