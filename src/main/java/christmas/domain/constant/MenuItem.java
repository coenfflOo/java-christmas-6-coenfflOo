package christmas.domain.constant;

public enum MenuItem {
    // 애피타이저
    PINE_MUSHROOM_SOUP(Category.APPETIZER,"양송이수프",6_000,2),
    TAPAS(Category.APPETIZER,"타파스", 5_500,2),
    CAESAR_SALAD(Category.APPETIZER,"시저샐러드",8_000,2),

    // 메인
    T_BONE_STEAK(Category.MAIN,"티본스테이크",55_000,1),
    BARBECUE_RIPS(Category.MAIN,"바비큐립",54_000,1),
    SEAFOOD_PASTA(Category.MAIN,"해산물파스타",35_000,1),
    CHRISTMAS_PASTA(Category.MAIN,"크리스마스파스타",25_000,1),

    // 디저트
    CHOCOLATE_CAKE(Category.DESSERT,"초코케이크",15_000,0),
    ICE_CREAM(Category.DESSERT,"아이스크림",5_000,0),

    // 음료
    ZERO_COKE(Category.DRINK,"제로콜라",3_000,2),
    RED_WINE(Category.DRINK,"레드와인",60_000,2),
    CHAMPAGNE(Category.DRINK,"샴페인",25_000,2)
    ;
    private final Category menu;
    private final String dish;
    private final int price;

    private final int weekend;

    MenuItem(Category menu, String dish, int price, int weekend) {
        this.menu = menu;
        this.dish = dish;
        this.price = price;
        this.weekend = weekend;
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

    public int getWeekend() {
        return weekend;
    }

    // public void 이넘뽑기(){ for(MenuItem menu : values()){ } }
}
