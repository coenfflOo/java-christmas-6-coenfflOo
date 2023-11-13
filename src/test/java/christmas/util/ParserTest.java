package christmas.util;

import christmas.exception.ChristmasException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    @DisplayName("숫자 외의 값 입력시 예외 발생")
    void parseStringToInt_InvalidInput_IllegalArgumentException(String input) {
        assertThatThrownBy(() -> Parser.parseStringToInt(input))
                .isInstanceOf(ChristmasException.class)
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"Turkey-10,Ham-20,Pie-15"})
    @DisplayName("올바른 메뉴 입력 시 파싱 여부")
    void parseStringToMap_ValidInput_ReturnsMap(String input) {
        Map<String, Integer> result = Parser.parseStringToMap(input);
        assertThat(result).isNotEmpty();
        assertThat(result.keySet()).containsExactly("Turkey");
        assertThat(result.values()).containsExactly(10);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Turkey10,Ham-20,Pie-15",
            "Cake-25,Cookies-5Eggnog-12",
            "Cake-25,Cookies 5,Eggnog-12",
            "Turkey-10,Ham-20/Pie-15",
            "Cake-25,Cookies-5,Eggnog-12,",
            "abc- def",
            "ghi - jkl",
            "mno -pqr",})
    void parseStringToMap_InvalidMenuFormat_IllegalArgumentException(String input) {
        assertThatThrownBy(() -> Parser.parseStringToMap(input))
                .isInstanceOf(ChristmasException.class)
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Turkey-10,Ham-20,Turkey-15",
            "Cake-25,Cookies-5,Eggnog-12,Eggnog-8",})
    void parseStringToMap_InvalidMenuFormat_IllegalStateException(String input) {
        assertThatThrownBy(() -> Parser.parseStringToMap(input))
                .isInstanceOf(IllegalStateException.class);
    }

}
