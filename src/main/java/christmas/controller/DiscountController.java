package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.service.PromotionEventService;
import christmas.view.OutputView;

public class DiscountController {
    private final Date date;
    private final Menu menu;

    private DiscountController(Date date, Menu menu) {
        this.date = date;
        this.menu = menu;
        discountBenefits();
    }

    public static Discount from(final Date date, final Menu menu) {
        new DiscountController(date,menu);
        return new Discount();
    }

    private void discountBenefits(){
        checkReservation();
        checkPromotion();
    }

    private void checkReservation() {
        OutputView.printReservationCheck(date, menu); // 날짜, 주문 확인 출력
        OutputView.printOriginalPrice(menu.getOriginalPrice()); // 할인 전 총 주문 금액
    }

    private void checkPromotion() {
        PromotionEventService promotionEventService = new PromotionEventService(menu);
        OutputView.printPromotion(promotionEventService.isChampagnePromotionApplied()); // 증정 메뉴
    }

}


