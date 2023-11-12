package christmas.domain;

import christmas.constant.menu.Category;
import christmas.constant.menu.MenuItem;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Menu {
    private static final int MAX_MENU_COUNT = 20;

    private Map<String, Integer> orderMenu;
    private static final Map<String, MenuItem> MENU_MAP = initMenuMap();


    private Menu(Map<String, Integer> orderMenu) {
        validateMenu(orderMenu);
        this.orderMenu = orderMenu;
    }

    public static Menu from(final Map<String, Integer> menu) {
        return new Menu(menu);
    }

    private void validateMenu(final Map<String, Integer> menu) {
        validateMenuExistence(menu);
        validateMenuQuantity(menu);
        validateDuplicateMenu(menu);
        validateDrinkOnly(menu);
        validateMenuCount(menu);
    }

    private void validateMenuExistence(final Map<String, Integer> menu) {
        for (String menuKey : menu.keySet()) {
            if (!MENU_MAP.containsKey(menuKey)) {
                throw new IllegalArgumentException(); //메뉴판에 없는 메뉴
            }
        }
    }

    private void validateMenuQuantity(final Map<String, Integer> menu) {
        for (int quantity : menu.values()) {
            if (quantity < 1) {
                throw new IllegalArgumentException(); //메뉴의 개수는 1 이상
            }
        }
    }

    private void validateDuplicateMenu(final Map<String, Integer> menu) {
        long distinctMenuCount = menu.keySet().stream().distinct().count();
        if (distinctMenuCount < menu.size()) {
            throw new IllegalArgumentException(); //중복된 메뉴
        }
    }

    private void validateDrinkOnly(final Map<String, Integer> menu) {
        boolean containsNonDrink = menu.keySet().stream()
                .anyMatch(menuKey -> MENU_MAP.get(menuKey).getMenu() != Category.DRINK);

        if (!containsNonDrink) {
            throw new IllegalArgumentException();
        }
    }

    private void validateMenuCount(final Map<String, Integer> menu) {
        if (menu.size() > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(); //최대 메뉴 수 20
        }
    }

    private static Map<String, MenuItem> initMenuMap() {
        return Arrays.stream(MenuItem.values())
                .collect(Collectors.toMap(MenuItem::getDish, Function.identity()));
    }

    public int getOriginalPrice() {
        return orderMenu.entrySet().stream()
                .mapToInt(this::calculateMenuItemTotalPrice)
                .sum();
    }

    private int calculateMenuItemTotalPrice(Map.Entry<String, Integer> entry) {
        String menuKey = entry.getKey();
        int quantity = entry.getValue();
        MenuItem menuItem = MENU_MAP.get(menuKey);
        return menuItem.getPrice() * quantity;
    }

    public Map<String, Integer> getOrderMenu() {
        return orderMenu;
    }
}
