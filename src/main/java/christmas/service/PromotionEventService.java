package christmas.service;

import static christmas.view.constant.OutputMessage.APPLY_GIFT;
import static christmas.view.constant.OutputMessage.NOTHING_GIFT;

import christmas.domain.Menu;
import christmas.view.constant.OutputMessage;
import christmas.view.constant.PrintFormat;

public class PromotionEventService {

    private boolean champagnePromotionApplied;

    public PromotionEventService(Menu menu) {
        if (isChampagnePromotionEligible(menu.getOriginalPrice())) {
            champagnePromotionApplied = true;
        }
    }

    private boolean isChampagnePromotionEligible(int price){
        return price >= 120000; // 상수처리
    }

    public String isChampagnePromotionApplied() {
        return OutputMessage.getPromotion(champagnePromotionApplied);
    }
}
