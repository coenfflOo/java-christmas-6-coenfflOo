package christmas.controller;

import christmas.domain.Date;

public class ChristmasMainController {
    private ChristmasMainController() {
    }

    public static void start(){
        Date date = DateController.requestDate();
    }
}
