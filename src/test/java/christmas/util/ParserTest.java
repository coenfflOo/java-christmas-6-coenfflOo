package christmas.util;

import christmas.exception.ChristmasException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

public class ParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"123", "46", "7"})
    @DisplayName("숫자를 입력했을 때 정수로 변환되는지 테스트")
    void parseStringToInt_ValidInput_ReturnsInteger(String input) {
        int result = Parser.parseStringToInt(input);
        assertThat(result).isInstanceOf(Integer.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "12/25", "3rd"})
    @DisplayName("숫자 외의 값 입력시 예외 발생")
    void parseStringToInt_InvalidInput_NumberFormatException(String input) {
        assertThatThrownBy(() -> Parser.parseStringToInt(input))
                .isInstanceOf(ChristmasException.class)
                .hasCauseInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {" 12", "5 8", "31 ", "", " "})
    @DisplayName("숫자 외의 공백 포함 값 입력시 예외 발생")
    void parseStringToInt_InvalidInput_IllegalArgumentException(String input) {
        assertThatThrownBy(() -> Parser.parseStringToInt(input))
                .isInstanceOf(ChristmasException.class)
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상적인 메뉴 문자열을 Map으로 파싱")
    void parseValidMenuToMap() {
        // Given
        String validMenu = "양송이수프-2,해산물파스타-3";

        // When
        Map<String, Integer> result = Parser.parseStringToMap(validMenu);

        // Then
        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of("양송이수프", 2, "해산물파스타", 3));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "티본스테이크-10,레드와인-5,티본스테이크-1",
            " ",
            "",
            "바비큐립-1,해산물파스타-2,",
            "바비큐립 4,양송이수프-2",
            "티본스테이크-10, 레드와인-5",
            "레드와인5,티본스테이크-1"
    })
    @DisplayName("parseStringToMap - Invalid input")
    void parseStringToMap_InvalidInput(String input) {
        assertThatExceptionOfType(ChristmasException.class)
                .isThrownBy(() -> Parser.parseStringToMap(input))
                .withCauseExactlyInstanceOf(IllegalArgumentException.class);
    }

}
