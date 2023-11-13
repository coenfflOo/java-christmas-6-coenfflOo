package christmas.service;


import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountItem;
import christmas.domain.constant.MenuItem;
import christmas.util.DateUtil;
import java.util.Map;

public class WeekEventService {

    private final Date date;
    private final Menu menu;
    private static int eventNumber;

    public WeekEventService(Date date, Menu menu) {
        eventNumber = DateUtil.isWeekend(date.getDate());
        this.date = date;
        this.menu = menu;
    }

    public int checkWeekDiscount(Discount discount) {
        int price = countWeekendMenuItems() * 2023;
        discount.addEventApplied(DiscountItem.getWeekDiscount(eventNumber),price);
        return price;
    }

    public int checkSpecialDiscount(Discount discount) {
        if (DateUtil.isSpecialDay(date.getDate())) {
            discount.addEventApplied(DiscountItem.SPECIAL_DISCOUNT, 1000); //상수처리
            return 1000;
        }
        return 0;
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
