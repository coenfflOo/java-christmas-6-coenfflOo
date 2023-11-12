package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.service.PromotionEventService;
import christmas.view.output.OutputView;

public class EventController {
    Date date;
    Menu menu;

    private EventController() {
    }

    public EventController(Date date, Menu menu) {
        this.date = date;
        this.menu = menu;
    }

    public void checkReservation() {
        OutputView.printReservationCheck(date, menu);
        OutputView.printOriginalPrice(menu.getOriginalPrice());
    }

    public void printOfEvents(){
        PromotionEventService promotionEventService = new PromotionEventService(menu);
        OutputView.printPromotion(promotionEventService.isChampagnePromotionApplied());
    }

}
