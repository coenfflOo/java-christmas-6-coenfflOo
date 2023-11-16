package christmas.service;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.BadgeItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitServiceTest {

    Date date;
    Menu menu;
    Discount discount;
    Map<String, Integer> orderMenu;
    ChristmasDDayService christmasDDayService;
    WeekEventService weekEventService;
    PromotionEventService promotionEventService;

    @BeforeEach
    void init() {
        christmasDDayService = new ChristmasDDayService();
        orderMenu = new HashMap<>();
        discount = new Discount();
    }

    @Test
    @DisplayName("총 혜택 계산 테스트 - 최소 주문 가격 미달")
    void totalBenefitsBelowMinOrderPrice() {
        // Given
        date = Date.from(26);
        orderMenu.put("시저샐러드", 1);
        menu = Menu.from(orderMenu);
        BenefitService benefitService = new BenefitService(date, menu, discount);

        // When
        int totalBenefits = benefitService.totalBenefits();

        // Then
        assertThat(totalBenefits).isEqualTo(0);
    }

    @Test
    @DisplayName("총 혜택 계산 테스트 - 최소 주문 가격 이상, 특별한 날, 주간 할인, 프로모션 할인")
    void totalBenefitsAboveMinOrderPriceWithSpecialDayAndDiscounts() {
        // Given
        date = Date.from(25);
        orderMenu.put("티본스테이크", 2);
        orderMenu.put("초코케이크", 3);
        menu = Menu.from(orderMenu);
        BenefitService benefitService = new BenefitService(date, menu, discount);
        weekEventService = new WeekEventService(date, menu);
        promotionEventService = new PromotionEventService(menu);

        // When
        int totalBenefits = benefitService.totalBenefits();

        // Then
        assertThat(totalBenefits).isEqualTo(
                christmasDDayService.checkChristmasDiscount(date, discount) +
                        weekEventService.checkWeekDiscount(discount) +
                        weekEventService.checkSpecialDiscount(discount) +
                        promotionEventService.checkPromotionDiscount(discount)
        );
    }

    @Test
    @DisplayName("혜택 적용 후 가격 계산 테스트")
    void afterBenefitsCalculation() {
        // Given
        date = Date.from(15);
        orderMenu.put("티본스테이크", 2);
        orderMenu.put("초코케이크", 3);
        menu = Menu.from(orderMenu);
        BenefitService benefitService = new BenefitService(date, menu, discount);
        weekEventService = new WeekEventService(date, menu);

        // When
        int afterBenefits = benefitService.afterBenefits();

        // Then
        assertThat(afterBenefits).isEqualTo(
                menu.getOriginalPrice() - (christmasDDayService.checkChristmasDiscount(date, discount) +
                        weekEventService.checkWeekDiscount(discount) +
                        weekEventService.checkSpecialDiscount(discount)));
    }

    @Test
    @DisplayName("혜택 뱃지 아이콘 확인 테스트")
    void benefitBadgeIconVerification() {
        // Given
        int totalBenefits = 3_200;
        BenefitService benefitService = new BenefitService(
                Date.from(23),  // Example: November 1, 2023
                Menu.from(Map.of("타파스", 2, "초코케이크", 3)),
                new Discount()
        );

        // When
        String badgeIcon = benefitService.benefitBadge();

        // Then
        assertThat(badgeIcon).isEqualTo(BadgeItem.getIconForValue(totalBenefits));
    }
}
