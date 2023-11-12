package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.view.output.OutputView;

public class ChristmasMainController {
    private ChristmasMainController() {
    }

    public static void start(){
        Date date = DateController.requestDate();
        Menu menu = MenuController.requestMenu();
        OutputView.printReservationCheck(date,menu);
    }
}
