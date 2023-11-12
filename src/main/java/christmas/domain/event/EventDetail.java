package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
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
            orders -> true,
            (date, orders) -> BASE_DISCOUNT + calculatePassedDays(date) * CHRISTMAS_RATE),
    WEEKDAYS(
            "평일 할인",
            IntStream.rangeClosed(EVENT_START, EVENT_END)
                    .filter(i -> i % 7 >= 3 || i % 7 == 0).boxed()
                    .toList(),
            10_000,
            orders -> orders.existsOrderItemByCategory(DESSERT),
            (date, orders) -> orders.countOrderItemByCategory(DESSERT) * DAILY_RATE),
    WEEKENDS(
            "주말 할인",
            IntStream.rangeClosed(EVENT_START, EVENT_END)
                    .filter(i -> i % 7 == 1 || i % 7 == 2).boxed()
                    .toList(),
            10_000,
            orders -> orders.existsOrderItemByCategory(MAIN),
            (date, orders) -> orders.countOrderItemByCategory(MAIN) * DAILY_RATE),
    SPECIAL(
            "특별 할인",
            List.of(3, 10, 17, 24, 25, 31),
            10_000,
            orders -> true,
            (date, orders) -> BASE_DISCOUNT),
    GIVE_AWAY(
            "증정 이벤트",
            IntStream.rangeClosed(EVENT_START, EVENT_END)
                    .boxed()
                    .toList(),
            120_000,
            orders -> true,
            (date, orders) -> NO_DISCOUNT);

    private final String eventName;
    private final List<Integer> dateCondition;
    private final long priceCondition;
    private final Function<Orders, Boolean> itemCondition;
    private final BiFunction<VisitingDate, Orders, Long> discountCalculator;

    EventDetail(String eventName, List<Integer> dateCondition, long priceCondition,
                Function<Orders, Boolean> itemCondition,
                BiFunction<VisitingDate, Orders, Long> discountCalculator) {
        this.eventName = eventName;
        this.dateCondition = dateCondition;
        this.priceCondition = priceCondition;
        this.itemCondition = itemCondition;
        this.discountCalculator = discountCalculator;
    }

    private static long calculatePassedDays(VisitingDate date) {
        return date.getDate() - EVENT_START;
    }

    public static List<EventDetail> findByCondition(VisitingDate date, Orders orders) {
        return Arrays.stream(EventDetail.values())
                .filter(condition -> condition.dateCondition.contains(date.getDate()))
                .filter(condition -> orders.calculateOriginalTotalAmount() >= condition.priceCondition)
                .filter(condition -> condition.itemCondition.apply(orders))
                .toList();
    }

    public static long getGiveAwayPrice() {
        return GIVE_AWAY_PRICE;
    }

    public long calculateDiscountAmount(VisitingDate date, Orders orders) {
        return discountCalculator.apply(date, orders);
    }

    public String getEventName() {
        return eventName;
    }
}
