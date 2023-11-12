package christmas.domain;

import christmas.domain.constant.DiscountItem;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private Map<DiscountItem, Integer> discounts;

    public Discount() {
        this.discounts = new HashMap<>();
    }

    public void addEventApplied(DiscountItem discountItem, int discountAmount) {
        if (discountItem != null && discountAmount < 0) {
            discounts.put(discountItem, discountAmount);
        }
    }

    public Map<DiscountItem, Integer> getDiscounts() {
        return Collections.unmodifiableMap(discounts);
    }
}
