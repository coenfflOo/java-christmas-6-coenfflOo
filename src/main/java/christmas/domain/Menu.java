package christmas.domain;

import java.util.Map;

public class Menu {
    private Map<String, Integer> orderMenu;

    private Menu(Map<String, Integer> orderMenu) {
        this.orderMenu = orderMenu;
    }

    public static Menu from(final Map<String, Integer> menu) {
        return new Menu(menu);
    }
}
