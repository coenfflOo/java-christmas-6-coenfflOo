package christmas.view.input;

import christmas.domain.Date;
import christmas.util.Parser;
import christmas.util.InputUtil;

public class InputView {
    public static Date readDateInfo() {
        try {
            final String dateInput = InputUtil.readLine();
            final int date = Parser.parseStringToInt(dateInput);
            return Date.from(date);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return readDateInfo();
        }
    }
}
