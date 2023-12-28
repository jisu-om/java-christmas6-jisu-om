package christmas.domain.event.discount;

import christmas.domain.menu.MenuType;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

public class WeekdayDiscount extends Discount {
    private static final String WEEKDAY_DISCOUNT_NAME = "평일 할인";
    private static final int BENEFIT_PER_MENU = 2_023;
    private static final MenuType DISCOUNT_TYPE = MenuType.DESSERT;
    private final Orders orders;

    public WeekdayDiscount(VisitingDate date, Orders orders) {
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
        return date.isWeekday() && orders.containsMenuType(DISCOUNT_TYPE);
    }

    @Override
    public String getName() {
        return WEEKDAY_DISCOUNT_NAME;
    }
}
