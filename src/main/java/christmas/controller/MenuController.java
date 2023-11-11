package christmas.controller;

import static christmas.constant.Message.InputMessage.REQUEST_MENU;
import static christmas.view.input.InputView.readDateInfo;
import static christmas.view.input.InputView.readMenuInfo;

import christmas.domain.Menu;
import christmas.view.output.OutputView;

public class MenuController {

    private MenuController() {
    }

    public static Menu requestMenu(){
        OutputView.println(REQUEST_MENU.getMessage());
        return readMenuInfo();
    }
}
