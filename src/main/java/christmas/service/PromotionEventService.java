package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountItem;
import christmas.domain.constant.MenuItem;
import christmas.view.constant.OutputMessage;

public class PromotionEventService {
    private final int PROMOTION_STANDARD = 120_000;

    private boolean champagnePromotionApplied;

    public PromotionEventService(Menu menu) {
        if (isChampagnePromotionEligible(menu.getOriginalPrice())) {
            champagnePromotionApplied = true;
        }
    }

    public int checkPromotionDiscount(Discount discount) {
        if (champagnePromotionApplied) {
            discount.addEventApplied(DiscountItem.PROMOTION_DISCOUNT, MenuItem.CHAMPAGNE.getPrice());
            return MenuItem.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    private boolean isChampagnePromotionEligible(int price) {
        return price >= PROMOTION_STANDARD;
    }

    public String isChampagnePromotionApplied() {
        return OutputMessage.getPromotion(champagnePromotionApplied);
    }
}
