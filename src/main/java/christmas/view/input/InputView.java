package christmas.view.input;

import christmas.service.Parser;
import christmas.util.InputUtil;

public class InputView {
    public static int readDateInfo() {
        try {
            final String dateInput = InputUtil.readLine();
            final int date = Parser.parseStringToInt(dateInput);

            return date;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return readDateInfo();
        }
    }
}
