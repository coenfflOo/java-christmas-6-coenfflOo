package christmas.service;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.BadgeItem;

public class BenefitService {
    private final Date date;
    private final Menu menu;
    private final Discount discount;

    public BenefitService(Date date, Menu menu, Discount discount) {
        this.date = date;
        this.menu = menu;
        this.discount = discount;
    }


    public int totalBenefits(){
        if (menu.getOriginalPrice() < 10000)
            return 0;
        return christmasDday() + weeklyDiscount() + promotionDiscount();
    }

    public int afterBenefits(){
        return menu.getOriginalPrice() + onlyDiscountBenefits();
    }

    public String benefitBadge(){
        return BadgeItem.getIconForValue(Math.abs(totalBenefits()));
    }

    private int onlyDiscountBenefits(){
        if (menu.getOriginalPrice() < 10000)
            return 0;
        return christmasDday() + weeklyDiscount();
    }

    private int christmasDday(){
        ChristmasDdayService christmasDdayService = new ChristmasDdayService();
        return christmasDdayService.checkChristmasDiscount(date,discount);
    }

    private int weeklyDiscount(){
        WeekEventService weekEventService = new WeekEventService(date,menu);
        return weekEventService.checkWeekDiscount(discount) +
        weekEventService.checkSpecialDiscount(discount);
    }

    private int promotionDiscount(){
        PromotionEventService promotionEventService = new PromotionEventService(menu);
        return promotionEventService.checkPromotionDiscount(discount);
    }
}
