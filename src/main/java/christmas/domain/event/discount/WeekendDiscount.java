package christmas.domain.event.discount;

import christmas.domain.menu.MenuType;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

public class WeekendDiscount extends Discount {
    private static final String WEEKEND_DISCOUNT_NAME = "주말 할인";
    private static final int BENEFIT_PER_MENU = 2_023;
    private static final MenuType DISCOUNT_TYPE = MenuType.MAIN;
    private final Orders orders;

    public WeekendDiscount(VisitingDate date, Orders orders) {
        super(date);
        this.orders = orders;
    }

    @Override
    public int getBenefitAmount() {
        if (!isApplied()) {
            return 0;
        }
        return orders.countMenuType(DISCOUNT_TYPE) * BENEFIT_PER_MENU;
    }

    @Override
    public boolean isApplied() {
        return date.isWeekend() && orders.containsMenuType(DISCOUNT_TYPE);
    }

    @Override
    public String getName() {
        return WEEKEND_DISCOUNT_NAME;
    }
}
