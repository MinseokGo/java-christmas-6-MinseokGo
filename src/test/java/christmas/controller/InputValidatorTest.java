package christmas.controller;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.utils.ErrorConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest extends NsTest {
    private final InputValidator inputValidator = InputValidator.getInstance();

    @DisplayName("방문 날짜 입력은 숫자 형식이다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "14", "31"})
    void dateInputIsNumberFormat(final String input) {
        inputValidator.visitDate(input);
        assertThat(output()).contains("");
    }

    @DisplayName("실패 : 방문 날짜 입력이 숫자 형식이 아니다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "_!", "히히", "A1234"})
    void dateInputIsNotNumberFormat(final String input){
        inputValidator.visitDate(input);
        assertThat(output()).contains(ErrorConstants.DATE_NOT_VALID_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "15", "31"})
    @DisplayName("방문 날짜는 1에서 31사이의 숫자이다.")
    void dateBoundaryIs1to31(final String input) {
        inputValidator.visitDate(input);
        assertThat(output()).contains("");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    @DisplayName("실패 : 방문 날짜는 1에서 31사이의 숫자가 아니다.")
    void dateBoundaryIsNot1to31(final String input) {
        inputValidator.visitDate(input);
        assertThat(output()).contains(ErrorConstants.DATE_NOT_VALID_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1,티본스테이크-2", "초코케이크-3,타파스-10"})
    @DisplayName("주문 입력 형식은 '이름-숫자' 와 같다.")
    void orderInputFormatIsNameDashNumber(final String input) {
        inputValidator.orderMenuAndNumberOfMenu(input);
        assertThat(output()).contains("");
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1a,티본스테이크_2", "초코케이크@3,타파스-10"})
    @DisplayName("실패 : 주문 입력 형식은 '이름-숫자'가 아니다.")
    void orderInputFormatIsNotNameDashNumber(final String input) {
        inputValidator.orderMenuAndNumberOfMenu(input);
        assertThat(output()).contains(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"감바스바스-1,티본스테이크-2", "초코초코바닐라-3,타파스-10"})
    @DisplayName("실패 : 주문 입력에 존재하지 않는 메뉴를 입력한다.")
    void orderInputIsNotExistsMenu(final String input) {
        inputValidator.orderMenuAndNumberOfMenu(input);
        assertThat(output()).contains(ErrorConstants.MENU_INPUT_NOT_VALID_MESSAGE);
    }

    @Override
    protected void runMain() {
    }
}