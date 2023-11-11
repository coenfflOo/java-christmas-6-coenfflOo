package christmas.controller;

import static christmas.constant.Message.InputMessage.REQUEST_DATE;
import static christmas.view.input.InputView.readDateInfo;

import christmas.view.output.OutputView;

public class DateController {
    private DateController() {
    }

    public static int requestDate(){
        OutputView.println(REQUEST_DATE.getMessage());
        return readDateInfo();
    }
}
