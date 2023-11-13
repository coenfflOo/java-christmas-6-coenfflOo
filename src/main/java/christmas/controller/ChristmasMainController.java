package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;

public class ChristmasMainController {
    private ChristmasMainController() {
    }

    public static void start(){
        Date date = DateController.requestDate();
        Menu menu = MenuController.requestMenu();
        Discount discount = DiscountController.from(date,menu);
        BenefitsController.from(date,menu,discount);
    }
}
