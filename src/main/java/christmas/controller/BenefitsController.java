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
        new BenefitsController(date, menu, discount);
    }

    private void benefitEvents() {
        checkBenefits();
        applyBenefits();
    }

    public void checkBenefits() {
        int totalMoney = benefitService.totalBenefits();
        OutputView.printDiscounts(discount);
        OutputView.printTotalBenefits(totalMoney);
    }

    public void applyBenefits() {
        int payMoney = benefitService.afterBenefits();
        OutputView.printAfterBenefits(payMoney);
        OutputView.printBenefitBadge(benefitService.benefitBadge());
    }
}
