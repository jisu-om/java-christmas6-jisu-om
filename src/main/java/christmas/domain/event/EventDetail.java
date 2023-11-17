package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static christmas.domain.constants.DateConstants.*;
import static christmas.domain.constants.BenefitConstants.*;
import static christmas.domain.orders.MenuCategory.DESSERT;
import static christmas.domain.orders.MenuCategory.MAIN;

public enum EventDetail {
    CHRISTMAS_D_DAY(
            "크리스마스 디데이 할인",
            VisitingDate::isChristmasEventActive,
            BASE_PRICE_CONDITION,
            orders -> true,
            (date, orders) -> BASE_DISCOUNT + calculatePassedDays(date) * CHRISTMAS_RATE),
    WEEKDAYS(
            "평일 할인",
            VisitingDate::isWeekday,
            BASE_PRICE_CONDITION,
            orders -> orders.existsOrderItemByCategory(DESSERT),
            (date, orders) -> orders.countOrderItemByCategory(DESSERT) * DAILY_RATE),
    WEEKENDS(
            "주말 할인",
            VisitingDate::isWeekend,
            BASE_PRICE_CONDITION,
            orders -> orders.existsOrderItemByCategory(MAIN),
            (date, orders) -> orders.countOrderItemByCategory(MAIN) * DAILY_RATE),
    SPECIAL(
            "특별 할인",
            VisitingDate::isSpecialDay,
            BASE_PRICE_CONDITION,
            orders -> true,
            (date, orders) -> BASE_DISCOUNT),
    GIVE_AWAY(
            "증정 이벤트",
            VisitingDate::meetsGiveAwayDateCondition,
            GIVE_AWAY_PRICE_CONDITION,
            orders -> true,
            (date, orders) -> GIVE_AWAY_PRICE);

    private final String eventName;
    private final Predicate<VisitingDate> dateCondition;
    private final long priceCondition;
    private final Function<Orders, Boolean> itemCondition;
    private final BiFunction<VisitingDate, Orders, Long> benefitCalculator;

    EventDetail(String eventName, Predicate<VisitingDate> dateCondition, long priceCondition,
                Function<Orders, Boolean> itemCondition,
                BiFunction<VisitingDate, Orders, Long> benefitCalculator) {
        this.eventName = eventName;
        this.dateCondition = dateCondition;
        this.priceCondition = priceCondition;
        this.itemCondition = itemCondition;
        this.benefitCalculator = benefitCalculator;
    }

    private static long calculatePassedDays(VisitingDate date) {
        return date.provideDate() - EVENT_START;
    }

    public static List<MatchingEvent> findByCondition(VisitingDate date, Orders orders) {
        return Arrays.stream(EventDetail.values())
                .filter(condition -> condition.dateCondition.test(date))
                .filter(condition -> orders.calculateOriginalTotalAmount() >= condition.priceCondition)
                .filter(condition -> condition.itemCondition.apply(orders))
                .map(eventDetail -> MatchingEvent.of(eventDetail, eventDetail.calculateBenefitAmount(date, orders)))
                .toList();
    }

    private long calculateBenefitAmount(VisitingDate date, Orders orders) {
        return benefitCalculator.apply(date, orders);
    }

    public String getEventName() {
        return eventName;
    }
}