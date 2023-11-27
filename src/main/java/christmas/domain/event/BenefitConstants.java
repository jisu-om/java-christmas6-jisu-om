package christmas.domain.event;

import christmas.domain.menu.Menu;

public enum BenefitConstants {
    ORDER_MINIMUM(10_000),
    GIVE_AWAY_MINIMUM(120_000),
    CHRISTMAS_D_DAY_BASE_BENEFIT(1_000),
    CHRISTMAS_D_DAY_DAILY_BENEFIT(100),
    WEEKDAY_BENEFIT_UNIT(2_023),
    WEEKEND_BENEFIT_UNIT(2_023),
    SPECIAL_BENEFIT(1_000),
    GIVE_AWAY_BENEFIT(Menu.샴페인.getPrice());

    private final long amount;

    BenefitConstants(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }
}
