package christmas.domain;

import christmas.exception.ChristmasException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class DateTest {

    @Test
    @DisplayName("Date 객체 생성 및 getDate() 메서드 확인")
    void createAndGetData() {
        int validDate = 15;
        Date date = Date.from(validDate);

        assertThat(date).isNotNull();
        assertThat(date.getDate()).isEqualTo(validDate);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32, 100, -5})
    @DisplayName("유효하지 않은 날짜로 Date 객체 생성 시 예외 발생")
    void createWithInvalidDate_ThrowsException(int invalidDate) {
        assertThatThrownBy(() -> Date.from(invalidDate))
                .isInstanceOf(ChristmasException.class)
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }
}
