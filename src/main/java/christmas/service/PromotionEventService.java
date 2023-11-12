package christmas.service;

import static christmas.view.constant.OutputMessage.APPLY_GIFT;
import static christmas.view.constant.OutputMessage.NOTHING_GIFT;

import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountItem;
import christmas.domain.constant.MenuItem;
import christmas.util.DateUtil;
import christmas.view.constant.OutputMessage;
import christmas.view.constant.PrintFormat;

public class PromotionEventService {

    private boolean champagnePromotionApplied;

    public PromotionEventService(Menu menu) {
        if (isChampagnePromotionEligible(menu.getOriginalPrice())) {
            champagnePromotionApplied = true;
        }
    }

    public void checkPromotionDiscount(Discount discount) {
        if (champagnePromotionApplied)
            discount.addEventApplied(DiscountItem.PROMOTION_DISCOUNT, MenuItem.CHAMPAGNE.getPrice()*(-1)); //상수처리
    }

    private boolean isChampagnePromotionEligible(int price){
        return price >= 120000; // 상수처리
    }

    public String isChampagnePromotionApplied() {
        return OutputMessage.getPromotion(champagnePromotionApplied);
    }
}
