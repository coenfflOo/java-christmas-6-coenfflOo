package christmas.domain;

import christmas.domain.constant.DiscountItem;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final Map<DiscountItem, Integer> discounts;

    public Discount() {
        this.discounts = new HashMap<>();
    }

    public void addEventApplied(DiscountItem discountItem, int discountAmount) {
        if (isValidDiscount(discountItem, discountAmount)) {
            discounts.put(discountItem, discountAmount);
        }
    }

    private boolean isValidDiscount(DiscountItem discountItem, int discountAmount) {
        return discountItem != null && discountAmount > 0;
    }

    public Map<DiscountItem, Integer> getDiscounts() {
        return Collections.unmodifiableMap(discounts);
    }
}
