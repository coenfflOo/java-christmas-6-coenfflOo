package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;
import christmas.domain.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InputViewTest {

    private static final String NEW_LINE = System.lineSeparator();

    @BeforeEach
    void setUp() {
        System.setIn(System.in);
    }
    @AfterEach
    void setOut() {
        System.setIn(System.in);
        Console.close();
    }

    @Test
    @DisplayName("올바른 날짜 입력 테스트")
    void readValidDateInfo() {
        // Given
        String input = "18" + NEW_LINE;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // When
        Date result = InputView.readDateInfo();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getDate()).isEqualTo(18);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 1", "12/21", "32", "invalidInput", ""," "})
    @DisplayName("잘못된 날짜 입력 시 재입력 테스트")
    void readInvalidDateInfo(String invalidInput) {
        // Given
        String input = invalidInput + NEW_LINE + "12" + NEW_LINE;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // When
        Date result = InputView.readDateInfo();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getDate()).isEqualTo(12);
    }

    @Test
    @DisplayName("올바른 메뉴 정보 입력 테스트")
    void readValidMenuInfo() {
        // Given
        String input = "레드와인-2,바비큐립-3" + NEW_LINE;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // When
        Menu result = InputView.readMenuInfo();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getOrderMenu()).containsAllEntriesOf(Map.of("레드와인", 2, "바비큐립", 3));
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalidInput", "", " "})
    @DisplayName("잘못된 메뉴 정보 입력 시 재입력 테스트")
    void readInvalidMenuInfo(String invalidInput) {
        // Given
        String input = invalidInput + NEW_LINE + "레드와인-2,크리스마스파스타-3" + NEW_LINE;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // When
        Menu result = InputView.readMenuInfo();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getOrderMenu()).containsAllEntriesOf(Map.of("레드와인", 2, "크리스마스파스타", 3));
    }
}
