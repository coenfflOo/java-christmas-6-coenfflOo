package christmas.view.constant;

import java.text.DecimalFormat;

public enum PrintFormat {

    RESPONSE_DATE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"+System.lineSeparator()),
    MENU_FORMAT("%s %d개"+System.lineSeparator()),
    SEPARATOR_FORMAT(new DecimalFormat("#,##0원") + System.lineSeparator()),
    BENEFIT_FORMAT("%s: %s" + System.lineSeparator());

    private final String format;
    private final DecimalFormat priceFormat;

    PrintFormat(String format) {
        this.format = format;
        this.priceFormat = null;
    }

    PrintFormat(DecimalFormat priceFormat) {
        this.format = null;
        this.priceFormat = priceFormat;
    }

    public String getFormat() {
        return format;
    }

    public DecimalFormat getPriceFormat() {
        return priceFormat;
    }

    public static String formatBenefit(String benefitName, int amount) {
        return String.format(BENEFIT_FORMAT.getFormat(), benefitName, SEPARATOR_FORMAT.getPriceFormat().format(amount));
    }
}
