package christmas.domain;

import static christmas.view.constant.ExceptionMessage.IS_INVALID_MENU;

import christmas.domain.constant.Category;
import christmas.domain.constant.MenuItem;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Menu {
    private static final int MAX_MENU_COUNT = 20;

    private final Map<String, Integer> orderMenu;
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
                throw new IllegalArgumentException(IS_INVALID_MENU.getMessage()); //메뉴판에 없는 메뉴
            }
        }
    }

    private void validateMenuQuantity(final Map<String, Integer> menu) {
        for (int quantity : menu.values()) {
            if (quantity < 1) {
                throw new IllegalArgumentException(IS_INVALID_MENU.getMessage()); //메뉴의 개수는 1 이상
            }
        }
    }

    private void validateDuplicateMenu(final Map<String, Integer> menu) {
        Set<String> uniqueMenuNames = new HashSet<>();

        if (menu.keySet().stream().anyMatch(name -> !uniqueMenuNames.add(name))) {
            throw new IllegalArgumentException(IS_INVALID_MENU.getMessage()); // 중복된 메뉴
        }
    }

    private void validateDrinkOnly(final Map<String, Integer> menu) {
        boolean containsNonDrink = menu.keySet().stream()
                .anyMatch(menuKey -> MENU_MAP.get(menuKey).getMenu() != Category.DRINK);

        if (!containsNonDrink) {
            throw new IllegalArgumentException(IS_INVALID_MENU.getMessage());
        }
    }

    private void validateMenuCount(final Map<String, Integer> menu) {
        int totalCount = menu.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        if (totalCount > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(IS_INVALID_MENU.getMessage());
        }
    }

    public static Map<String, MenuItem> initMenuMap() {
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
