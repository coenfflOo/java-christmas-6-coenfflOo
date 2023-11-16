package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilTest {

    @ParameterizedTest
    @ValueSource(ints = {4, 12, 14, 20, 21, 28})
    @DisplayName("평일이라면 2 출력 테스트")
    void isWeekend_Weekday_ReturnsZero(int weekday) {
        int result = DateUtil.isWeekend(weekday);
        assertThat(result).isZero();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @DisplayName("주말이라면 1 출력 테스트")
    void isWeekend_Weekend_ReturnsOne(int weekend) {
        int result = DateUtil.isWeekend(weekend);
        assertThat(result).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 12, 13, 22, 30})
    @DisplayName("별이 없는 날이라면 false 출력 테스트")
    void isSpecialDay_NotSpecialDay_ReturnsFalse(int notSpecialDay) {
        boolean result = DateUtil.isSpecialDay(notSpecialDay);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 31})
    @DisplayName("별이 있는 날이라면 true 출력 테스트")
    void isSpecialDay_Sunday_ReturnsTrue(int specialDay) {
        boolean result = DateUtil.isSpecialDay(specialDay);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("크리스마스라면 true 출력 테스트")
    void isSpecialDay_Christmas_ReturnsTrue() {
        int christmas = 25;
        boolean result = DateUtil.isSpecialDay(christmas);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 32, 0})
    @DisplayName("한달 이내의 날이 아니라면 true 출력 테스트")
    void isNotDayAMonth_DateOutOfRange_ReturnsTrue(int outOfRange) {
        boolean result = DateUtil.isNotDayAMonth(outOfRange);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 31, 15})
    @DisplayName("한달 이내라면 false 출력 테스트")
    void isNotDayAMonth_ValidDate_ReturnsFalse(int validDate) {
        boolean result = DateUtil.isNotDayAMonth(validDate);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("크리스마스 이전이라면 true 출력 테스트")
    void isBeforeChristmasDay_ChristmasDay_ReturnsTrue() {
        int christmasDay = 25;
        boolean result = DateUtil.isBeforeChristmasDay(christmasDay);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("크리스마스 이후라면 false 출력 테스트")
    void isBeforeChristmasDay_NotChristmasDay_ReturnsFalse() {
        int notChristmasDay = 26;
        boolean result = DateUtil.isBeforeChristmasDay(notChristmasDay);
        assertThat(result).isFalse();
    }
}
