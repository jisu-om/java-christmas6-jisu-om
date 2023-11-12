package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import static christmas.constants.DateConstants.*;
import static christmas.constants.DiscountConstants.*;
import static christmas.domain.menu.MenuCategory.DESSERT;
import static christmas.domain.menu.MenuCategory.MAIN;

public enum EventDetail {
    CHRISTMAS_D_DAY(
            "크리스마스 디데이 할인",
            IntStream.rangeClosed(EVENT_START, CHRISTMAS_EVENT_END)
                    .boxed()
                    .toList(),
            10_000,
            (orders, date) -> BASE_DISCOUNT + calculatePassedDays(date) * CHRISTMAS_RATE),
    WEEKDAYS(
            "평일 할인",
            IntStream.rangeClosed(EVENT_START, EVENT_END)
                    .filter(i -> i % 7 >= 3 || i % 7 == 0).boxed()
                    .toList(),
            10_000,
            (orders, date) -> orders.countOrderItemByCategory(DESSERT) * DAILY_RATE),
    WEEKENDS(
            "주말 할인",
            IntStream.rangeClosed(EVENT_START, EVENT_END)
                    .filter(i -> i % 7 == 1 || i % 7 == 2).boxed()
                    .toList(),
            10_000,
            (orders, date) -> orders.countOrderItemByCategory(MAIN) * DAILY_RATE),
    SPECIAL(
            "특별 할인",
            List.of(3, 10, 17, 24, 25, 31),
            10_000,
            (orders, date) -> BASE_DISCOUNT),
    GIVE_AWAY(
            "증정 이벤트",
            IntStream.rangeClosed(EVENT_START, EVENT_END)
                    .boxed()
                    .toList(),
            120_000,
            (orders, date) -> GIVE_AWAY_PRICE);

    private final String eventName;
    private final List<Integer> dateCondition;
    private final long priceCondition;
    private final BiFunction<Orders, VisitingDate, Long> discountCalculator;

    EventDetail(String eventName, List<Integer> dateCondition, long priceCondition,
                BiFunction<Orders, VisitingDate, Long> discountCalculator) {
        this.eventName = eventName;
        this.dateCondition = dateCondition;
        this.priceCondition = priceCondition;
        this.discountCalculator = discountCalculator;
    }

    private static long calculatePassedDays(VisitingDate date) {
        return date.getDate() - EVENT_START;
    }

    public static List<EventDetail> findByDateAndPriceCondition(VisitingDate date, long originalPrice) {
        return Arrays.stream(EventDetail.values())
                .filter(condition -> condition.dateCondition.contains(date.getDate()))
                .filter(condition -> originalPrice >= condition.priceCondition)
                .toList();
    }

    public long calculateDiscountAmount(Orders orders, VisitingDate date) {
        return discountCalculator.apply(orders, date);
    }
}
