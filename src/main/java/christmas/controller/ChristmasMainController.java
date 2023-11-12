package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Menu;

public class ChristmasMainController {
    private static EventController eventController;
    private ChristmasMainController() {
    }

    public static void start(){
        Date date = DateController.requestDate();
        Menu menu = MenuController.requestMenu();

        eventController = new EventController(date,menu);
        eventController.checkReservation();
        eventController.printOfEvents();


    }
}
