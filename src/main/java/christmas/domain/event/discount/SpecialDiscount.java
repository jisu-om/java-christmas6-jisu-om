package christmas.domain.event.discount;

import christmas.domain.visitingDate.VisitingDate;

public class SpecialDiscount extends Discount {
    private static final String SPECIAL_DISCOUNT_NAME = "특별 할인";
    private static final int BENEFIT_AMOUNT = 1_000;
    public SpecialDiscount(VisitingDate date) {
        super(date);
    }

    @Override
    public int getBenefitAmount() {
        if (!isApplied()) {
            return 0;
        }
        return BENEFIT_AMOUNT;
    }

    @Override
    public boolean isApplied() {
        return date.isSpecial();
    }

    @Override
    public String getName() {
        return SPECIAL_DISCOUNT_NAME;
    }
}
