package christmas.controller;

import static christmas.view.constant.InputMessage.REQUEST_DATE;
import static christmas.view.InputView.readDateInfo;

import christmas.domain.Date;
import christmas.view.OutputView;

public class DateController {
    private DateController() {
    }

    public static Date requestDate(){
        OutputView.println(REQUEST_DATE.getMessage());
        return readDateInfo();
    }
}
