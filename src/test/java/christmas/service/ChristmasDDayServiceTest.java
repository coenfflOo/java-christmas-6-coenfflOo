package christmas.service;


import christmas.domain.Date;
import christmas.domain.Discount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ChristmasDDayServiceTest {

    @ParameterizedTest
    @CsvSource({
            "24, 3300",
            "25, 3400",
            "23, 3200",
            "20, 2900",
            "26, 0"
    })
    @DisplayName("크리스마스 할인 계산 테스트")
    void checkChristmasDiscount(int day, int expectedDiscount) {
        // Given
        Date date = Date.from(day);
        ChristmasDDayService christmasDDayService = new ChristmasDDayService();

        // When
        int actualDiscount = christmasDDayService.checkChristmasDiscount(date, new Discount());

        // Then
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }
}