package christmas.view.output;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountItem;
import christmas.view.constant.*;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    public static void printReservationCheck(Date date, Menu menu){
        printDate(date.getDate());
        printOrderMenu(menu.getOrderMenu());
        printNewLine();
    }

    public static void printOriginalPrice(final int price){
        System.out.println(OutputMessage.RESPONSE_PRICE.getMessage());
        printSeparator(price);
        printNewLine();
    }

    public static void printPromotion(final String applyResult) {
        System.out.println(OutputMessage.RESPONSE_GIFT.getMessage());
        System.out.println(applyResult);
    }

    public static void printDiscounts(Discount discount) {
        System.out.println(OutputMessage.RESPONSE_BENEFIT.getMessage());
        for (Map.Entry<DiscountItem, Integer> entry : discount.getDiscounts().entrySet()) {
            printBenefit(entry.getKey().getDescription(), entry.getValue());
        }
    }

    private static void printDate(final int date) {
        System.out.printf(PrintFormat.RESPONSE_DATE.getFormat(), date);
        printNewLine();
    }

    private static void printOrderMenu(Map<String, Integer> orderMenu) {
        System.out.println(OutputMessage.RESPONSE_MENU.getMessage());
        for (Map.Entry<String, Integer> entry : orderMenu.entrySet()) {
            printMenu(entry.getKey(), entry.getValue());
        }
    }

    public static void println(final Object data) {
        System.out.println(data);
    }

    public static void printNewLine(){
        System.out.println();
    }

    private static void printMenu(final String dish, final int quantity) {
        System.out.printf(PrintFormat.MENU_FORMAT.getFormat(), dish, quantity);
    }

    public static void printSeparator(final int price) {
        System.out.printf(PrintFormat.SEPARATOR_FORMAT.formatPrice(price));
    }

    public static void printBenefit(final String benefit, final int price) {
        System.out.printf(PrintFormat.formatBenefit(benefit, price));
    }
}
