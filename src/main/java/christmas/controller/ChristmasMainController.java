package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;

public class ChristmasMainController {
    private static EventController eventController;
    private ChristmasMainController() {
    }

    public static void start(){
        Date date = DateController.requestDate();
        Menu menu = MenuController.requestMenu();
        Discount discount = new Discount();

        eventController = new EventController(date,menu,discount);
        eventController.checkReservation();
        eventController.printOfEvents();
        eventController.printBenefits();


    }
}
