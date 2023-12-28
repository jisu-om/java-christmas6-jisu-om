package christmas.domain.event.discount;

import christmas.domain.visitingDate.VisitingDate;

public class ChristmasDiscount extends Discount {
    private static final String CHRISTMAS_DISCOUNT_NAME = "크리스마스 디데이 할인";
    private static final int BASE_BENEFIT = 1_000;
    private static final int DAILY_BENEFIT = 100;

    public ChristmasDiscount(VisitingDate date) {
        super(date);
    }

    @Override
    public int getBenefitAmount() {
        if (!isApplied()) {
            return 0;
        }
        return BASE_BENEFIT + date.getBeforeDate() * DAILY_BENEFIT;
    }

    @Override
    public boolean isApplied() {
        return date.isBeforeChristmas();
    }

    @Override
    public String getName() {
        return CHRISTMAS_DISCOUNT_NAME;
    }
}
