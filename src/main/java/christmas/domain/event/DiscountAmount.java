package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.function.BiFunction;

import static christmas.constants.DateConstants.*;
import static christmas.constants.DiscountConstants.*;
import static christmas.domain.menu.MenuCategory.DESSERT;
import static christmas.domain.menu.MenuCategory.MAIN;

public enum DiscountAmount {
    CHRISTMAS_D_DAY((orders, date) -> BASE_DISCOUNT + calculatePassedDays(date) * CHRISTMAS_RATE),
    WEEKDAYS((orders, date) -> (orders.countOrderItemByCategory(DESSERT) * DAILY_RATE)),
    WEEKENDS((orders, date) -> (orders.countOrderItemByCategory(MAIN) * DAILY_RATE)),
    SPECIAL((orders, date) -> BASE_DISCOUNT),
    GIVE_AWAY((orders, date) -> GIVE_AWAY_PRICE),
    NONE((orders, date) -> NO_DISCOUNT);

    private final BiFunction<Orders, VisitingDate, Long> discountCalculator;

    DiscountAmount(BiFunction<Orders, VisitingDate, Long> discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    private static long calculatePassedDays(VisitingDate date) {
        return date.getDate() - EVENT_START;
    }

    public long calculateDiscountAmount(Orders orders, VisitingDate date) {
        return discountCalculator.apply(orders, date);
    }

}
