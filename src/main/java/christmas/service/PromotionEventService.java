package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountItem;
import christmas.domain.constant.MenuItem;
import christmas.view.constant.OutputMessage;

public class PromotionEventService {

    private boolean champagnePromotionApplied;

    public PromotionEventService(Menu menu) {
        if (isChampagnePromotionEligible(menu.getOriginalPrice())) {
            champagnePromotionApplied = true;
        }
    }

    public int checkPromotionDiscount(Discount discount) {
        if (champagnePromotionApplied) {
            discount.addEventApplied(DiscountItem.PROMOTION_DISCOUNT, MenuItem.CHAMPAGNE.getPrice() * (-1)); //상수처리
            return MenuItem.CHAMPAGNE.getPrice() * (-1);
        }
        return 0;
    }

    private boolean isChampagnePromotionEligible(int price){
        return price >= 120000; // 상수처리
    }

    public String isChampagnePromotionApplied() {
        return OutputMessage.getPromotion(champagnePromotionApplied);
    }
}
