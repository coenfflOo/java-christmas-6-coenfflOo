package christmas.service;


import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountItem;
import christmas.domain.constant.MenuItem;
import christmas.util.DateUtil;
import java.util.Map;

public class WeekEventService {

    private final Menu menu;
    private static int eventNumber;

    public WeekEventService(Date date, Menu menu) {
        eventNumber = DateUtil.isWeekend(date.getDate());
        this.menu = menu;
    }

    public void checkWeekDiscount(Discount discount) {
        int price = countWeekendMenuItems() * (-2023);
        discount.addEventApplied(DiscountItem.getWeekDiscount(eventNumber),price);
    }

    private int countWeekendMenuItems() {
        return menu.getOrderMenu().entrySet().stream()
                .filter(this::isMenuItemWeekend)
                .mapToInt(Map.Entry::getValue) // 각 메뉴의 주문 수량을 가져오기
                .sum(); // 주문 수량의 합을 반환
    }


    private boolean isMenuItemWeekend(Map.Entry<String, Integer> entry) {
        String menuKey = entry.getKey();
        MenuItem menuItem = Menu.initMenuMap().get(menuKey);
        return menuItem.getWeekend() == eventNumber;
    }


}
