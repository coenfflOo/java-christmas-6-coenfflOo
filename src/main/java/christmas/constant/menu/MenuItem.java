package christmas.constant.menu;

import static christmas.constant.menu.Category.*;

import christmas.constant.menu.Category;

public enum MenuItem {
    // 애피타이저
    PINE_MUSHROOM_SOUP(APPETIZER,"양송이수프",6_000),
    TAPAS(APPETIZER,"타파스", 5_500),
    CAESAR_SALAD(APPETIZER,"시저샐러드",8_000),

    // 메인
    T_BONE_STEAK(MAIN,"티본스테이크",55_000),
    BARBECUE_RIPS(MAIN,"바비큐립",54_000),
    SEAFOOD_PASTA(MAIN,"해산물파스타",35_000),
    CHRISTMAS_PASTA(MAIN,"크리스마스파스타",25_000),

    // 디저트
    CHOCOLATE_CAKE(DESSERT,"초코케이크",15_000),
    ICE_CREAM(DESSERT,"아이스크림",5_000),

    // 음료
    ZERO_COKE(DRINK,"제로콜라",3_000),
    RED_WINE(DRINK,"레드와인",60_000),
    CHAMPAGNE(DRINK,"샴페인",25_000)
    ;
    private final Category menu;
    private final String dish;
    private final int price;

    MenuItem(Category menu, String dish, int price) {
        this.menu = menu;
        this.dish = dish;
        this.price = price;
    }

    public Category getMenu() {
        return menu;
    }

    public String getDish() {
        return dish;
    }

    public int getPrice() {
        return price;
    }

    // public void 이넘뽑기(){ for(MenuItem menu : values()){ } }
}
