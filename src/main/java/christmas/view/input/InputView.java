package christmas.view.input;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.util.Parser;
import christmas.util.InputUtil;
import java.util.Map;

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

    public static Menu readMenuInfo() {
        try {
            final String menuInput = InputUtil.readLine();
            final Map<String, Integer> date = Parser.parseStringToMap(menuInput);
            return Menu.from(date);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return readMenuInfo();
        }
    }
}
