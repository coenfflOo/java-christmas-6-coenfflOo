package christmas.controller;

import static christmas.view.constant.InputMessage.REQUEST_MENU;
import static christmas.view.InputView.readMenuInfo;

import christmas.domain.Menu;
import christmas.view.OutputView;

public class MenuController {

    private MenuController() {
    }

    public static Menu requestMenu(){
        OutputView.println(REQUEST_MENU.getMessage());
        return readMenuInfo();
    }
}
