package christmas.domain;

import christmas.domain.constant.DiscountItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class DiscountTest {

    private Discount discount;

    @BeforeEach
    void setUp() {
        discount = new Discount();
    }

    @Test
    @DisplayName("Discount 객체 생성 및 getDiscounts() 확인")
    void createAndGetDiscounts() {
        assertThat(discount).isNotNull();
        assertThat(discount.getDiscounts()).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({"CHRISTMAS_DDAY_DISCOUNT, 10", "WEEKEND_DISCOUNT, 20", "PROMOTION_DISCOUNT, 15"})
    @DisplayName("유효한 할인 정보 추가 후 getDiscounts() 확인")
    void addValidDiscountAndGetDiscounts(DiscountItem discountItem, int discountAmount) {

        discount.addEventApplied(discountItem, discountAmount);

        assertThat(discount.getDiscounts()).hasSize(1);
        assertThat(discount.getDiscounts()).containsKey(discountItem);
        assertThat(discount.getDiscounts().get(discountItem)).isEqualTo(discountAmount);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10})
    @DisplayName("유효하지 않은 할인 정보 추가 시 무시")
    void addInvalidDiscount_ThrowsException(int invalidDiscountAmount) {
        discount.addEventApplied(DiscountItem.WEEKLY_DISCOUNT, invalidDiscountAmount);

        assertThat(discount.getDiscounts()).isEmpty();
    }

    @Test
    @DisplayName("getDiscounts()로 반환된 Map이 변경 불가능한지 확인")
    void getDiscountsReturnsUnmodifiableMap() {
        assertThatThrownBy(() -> discount.getDiscounts().put(DiscountItem.PROMOTION_DISCOUNT, 10))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
