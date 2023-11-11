package christmas.domain;

import java.util.function.BiFunction;

import static christmas.constants.DiscountConstants.*;
import static christmas.domain.MenuCategory.DESSERT;
import static christmas.domain.MenuCategory.MAIN;

public enum DiscountAmount {
    CHRISTMAS_D_DAY((orders, date) -> BASE_DISCOUNT + (date.getDate() - 1) * CHRISTMAS_RATE),
    WEEKDAYS((orders, date) -> (orders.countOrderItemByCategory(DESSERT) * DAILY_RATE)),
    WEEKENDS((orders, date) -> (orders.countOrderItemByCategory(MAIN) * DAILY_RATE)),
    SPECIAL((orders, date) -> BASE_DISCOUNT),
    GIVE_AWAY((orders, date) -> GIVE_AWAY_PRICE),
    NONE((orders, date) -> NO_DISCOUNT);

    private final BiFunction<Orders, VisitingDate, Long> discountCalculator;

    DiscountAmount(BiFunction<Orders, VisitingDate, Long> discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    public long calculateDiscountAmount(Orders orders, VisitingDate date) {
        return discountCalculator.apply(orders, date);
    }
}
