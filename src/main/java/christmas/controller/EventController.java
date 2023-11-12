package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountItem;
import christmas.service.ChristmasDdayService;
import christmas.service.PromotionEventService;
import christmas.view.output.OutputView;

public class EventController {
    Date date;
    Menu menu;
    Discount discount;
    ChristmasDdayService christmasDdayService = new ChristmasDdayService();

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
//        OutputView.printBenefits();
    }

    public void printBenefits(){
        christmasDdayService.checkEventDiscount(date,discount);

        OutputView.printDiscounts(discount);
    }

}
