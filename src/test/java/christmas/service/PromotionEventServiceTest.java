package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountItem;
import christmas.domain.constant.MenuItem;
import christmas.view.constant.OutputMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionEventServiceTest {

    @Test
    @DisplayName("샴페인 프로모션 적용 테스트 - 적용 대상")
    void applyChampagnePromotionEligible() {
        // Given
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put("초코케이크", 4);
        orderMenu.put("크리스마스파스타", 3);
        Menu menu = Menu.from(orderMenu);

        // When
        PromotionEventService promotionEventService = new PromotionEventService(menu);

        // Then
        assertThat(promotionEventService.isChampagnePromotionApplied()).isEqualTo(OutputMessage.getPromotion(true));
    }

    @Test
    @DisplayName("샴페인 프로모션 적용 테스트 - 적용 대상이 아님")
    void applyChampagnePromotionNotEligible() {
        // Given
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put("티본스테이크", 1);  // Total price less than PROMOTION_STANDARD
        Menu menu = Menu.from(orderMenu);

        // When
        PromotionEventService promotionEventService = new PromotionEventService(menu);

        // Then
        assertThat(promotionEventService.isChampagnePromotionApplied()).isEqualTo(OutputMessage.getPromotion(false));
    }

    @Test
    @DisplayName("프로모션 할인 테스트 - 적용 대상")
    void applyPromotionDiscount() {
        // Given
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put("초코케이크", 4);
        orderMenu.put("크리스마스파스타", 3);
        Menu menu = Menu.from(orderMenu);

        PromotionEventService promotionEventService = new PromotionEventService(menu);
        Discount discount = new Discount();

        // When
        int appliedDiscount = promotionEventService.checkPromotionDiscount(discount);

        // Then
        assertThat(appliedDiscount).isEqualTo(MenuItem.CHAMPAGNE.getPrice());
        assertThat(discount.getDiscounts()).containsKey(DiscountItem.PROMOTION_DISCOUNT);
        assertThat(discount.getDiscounts().get(DiscountItem.PROMOTION_DISCOUNT)).isEqualTo(MenuItem.CHAMPAGNE.getPrice());
    }

    @Test
    @DisplayName("프로모션 할인 테스트 - 적용 대상이 아님")
    void applyPromotionDiscountNotEligible() {
        // Given
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put("티본스테이크", 1);  // Total price less than PROMOTION_STANDARD
        Menu menu = Menu.from(orderMenu);

        PromotionEventService promotionEventService = new PromotionEventService(menu);
        Discount discount = new Discount();

        // When
        int appliedDiscount = promotionEventService.checkPromotionDiscount(discount);

        // Then
        assertThat(appliedDiscount).isEqualTo(0);
        assertThat(discount.getDiscounts()).doesNotContainKey(DiscountItem.PROMOTION_DISCOUNT);
    }

    // Add more tests as needed
}
