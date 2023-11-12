package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.service.BenefitService;
import christmas.service.ChristmasDdayService;
import christmas.service.PromotionEventService;
import christmas.service.WeekEventService;
import christmas.view.output.OutputView;

public class EventController {
    Date date;
    Menu menu;
    Discount discount;

    public EventController(Date date, Menu menu,Discount discount) {
        this.date = date;
        this.menu = menu;
        this.discount = discount;
    }

    public void checkReservation() {
        OutputView.printReservationCheck(date, menu);
        OutputView.printOriginalPrice(menu.getOriginalPrice());
    }

    public void printOfEvents(){
        PromotionEventService promotionEventService = new PromotionEventService(menu);
        OutputView.printPromotion(promotionEventService.isChampagnePromotionApplied());
    }

    public void printBenefits(){
        BenefitService benefitService = new BenefitService(date, menu, discount);
        int totalMoney = benefitService.totalBenefits();
        OutputView.printDiscounts(discount);
        OutputView.printTotalBenefits(totalMoney);
        int payMoney = benefitService.afterBenefits();
        OutputView.printAfterBenefits(payMoney);
    }

}
