package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.service.BenefitService;
import christmas.view.OutputView;

public class BenefitsController {
    private final Discount discount;
    private final BenefitService benefitService;

    private BenefitsController(Date date, Menu menu, Discount discount) {
        this.discount = discount;
        benefitService = new BenefitService(date, menu, discount);
        benefitEvents();
    }

    public static void from(final Date date, final Menu menu, final Discount discount) {
        new BenefitsController(date,menu,discount);
    }

    private void benefitEvents(){
        checkBenefits();
        applyBenefits();
    }

    public void checkBenefits(){
        int totalMoney = benefitService.totalBenefits();
        OutputView.printDiscounts(discount); // 혜택 내역
        OutputView.printTotalBenefits(totalMoney); // 총 혜택 금액
    }

    public void applyBenefits(){
        int payMoney = benefitService.afterBenefits(); //할인 후 예상 금액
        OutputView.printAfterBenefits(payMoney);
        OutputView.printBenefitBadge(benefitService.benefitBadge()); // 12월 이벤트 배지
    }
}
