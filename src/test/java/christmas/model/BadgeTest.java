package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {
    @DisplayName("혜택 금액이 특정 금액 이상이면 별 뱃지를 받는다.")
    @ParameterizedTest
    @ValueSource(strings = {"5000,별", "10000,트리", "20000,산타"})
    void getBadge(final String input) {
        final String[] inputs = input.split(",");
        final int price = Integer.parseInt(inputs[0]);
        final String name = inputs[1];

        assertThat(name).isEqualTo(Badge.getBadge(price).getName());
    }
}