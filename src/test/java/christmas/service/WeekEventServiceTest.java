package christmas.service;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WeekEventServiceTest {

    @Test
    @DisplayName("주말 할인 테스트")
    void checkWeekendDiscount() {
        // Given
        int weekendDate = 1;  // Saturday
        Date date = Date.from(weekendDate);
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put("바비큐립", 2);
        orderMenu.put("아이스크림", 3);
        Menu menu = Menu.from(orderMenu);

        WeekEventService weekEventService = new WeekEventService(date, menu);
        Discount discount = new Discount();

        // When
        int appliedDiscount = weekEventService.checkWeekDiscount(discount);

        // Then
        assertThat(appliedDiscount).isEqualTo(2 * 2023);  // Adjust based on your implementation
        assertThat(discount.getDiscounts()).containsKey(DiscountItem.getWeekDiscount(1));
        assertThat(discount.getDiscounts().get(DiscountItem.getWeekDiscount(1))).isEqualTo(2 * 2023);
    }

    @Test
    @DisplayName("평일 할인 테스트")
    void checkWeeklyDiscount() {
        // Given
        int weekendDate = 4;  // Saturday
        Date date = Date.from(weekendDate);
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put("바비큐립", 2);
        orderMenu.put("아이스크림", 3);
        Menu menu = Menu.from(orderMenu);

        WeekEventService weekEventService = new WeekEventService(date, menu);
        Discount discount = new Discount();

        // When
        int appliedDiscount = weekEventService.checkWeekDiscount(discount);

        // Then
        assertThat(appliedDiscount).isEqualTo(3 * 2023);  // Adjust based on your implementation
        assertThat(discount.getDiscounts()).containsKey(DiscountItem.getWeekDiscount(0));
        assertThat(discount.getDiscounts().get(DiscountItem.getWeekDiscount(0))).isEqualTo(3 * 2023);
    }

    @Test
    @DisplayName("특별 할인 테스트 - 특별한 날")
    void checkSpecialDiscountOnSpecialDay() {
        // Given
        int specialDate = 17;
        Date date = Date.from(specialDate);
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put("티본스테이크", 2);
        orderMenu.put("초코케이크", 3);
        Menu menu = Menu.from(orderMenu);

        WeekEventService weekEventService = new WeekEventService(date, menu);
        Discount discount = new Discount();

        // When
        int appliedDiscount = weekEventService.checkSpecialDiscount(discount);

        // Then
        assertThat(appliedDiscount).isEqualTo(1000);
        assertThat(discount.getDiscounts()).containsKey(DiscountItem.SPECIAL_DISCOUNT);
        assertThat(discount.getDiscounts().get(DiscountItem.SPECIAL_DISCOUNT)).isEqualTo(1000);
    }

    @Test
    @DisplayName("특별 할인 테스트 - 특별한 날이 아님")
    void checkSpecialDiscountOnOrdinaryDay() {
        // Given
        int ordinaryDate = 18;  // Example: November 1, 2023
        Date date = Date.from(ordinaryDate);
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put("티본스테이크", 2);
        orderMenu.put("초코케이크", 3);
        Menu menu = Menu.from(orderMenu);

        WeekEventService weekEventService = new WeekEventService(date, menu);
        Discount discount = new Discount();

        // When
        int appliedDiscount = weekEventService.checkSpecialDiscount(discount);

        // Then
        assertThat(appliedDiscount).isEqualTo(0);
        assertThat(discount.getDiscounts()).doesNotContainKey(DiscountItem.SPECIAL_DISCOUNT);
    }

}
