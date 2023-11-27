package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import static christmas.domain.event.BenefitConstants.*;
import static christmas.domain.menu.MenuType.DESSERT;
import static christmas.domain.menu.MenuType.MAIN;

public enum EventDetail {
    NONE(null,
            date -> false,
            orders -> orders.calculateTotalPrice() < ORDER_MINIMUM.getAmount(),
            (date, orders) -> 0L),
    CHRISTMAS_D_DAY("크리스마스 디데이 할인",
            VisitingDate::isBeforeChristmas,
            orders -> orders.calculateTotalPrice() >= ORDER_MINIMUM.getAmount(),
            (date, orders) -> CHRISTMAS_D_DAY_BASE_BENEFIT.getAmount()
                    + date.getChristmasDDayBenefitDate() * CHRISTMAS_D_DAY_DAILY_BENEFIT.getAmount()),
    WEEKDAY("평일 할인",
            VisitingDate::isWeekday,
            orders -> orders.calculateTotalPrice() >= ORDER_MINIMUM.getAmount() && orders.containsMenuType(DESSERT),
            (date, orders) ->  orders.countMenuType(DESSERT) * WEEKDAY_BENEFIT_UNIT.getAmount()),
    WEEKEND("주말 할인",
            VisitingDate::isWeekend,
            orders -> orders.calculateTotalPrice() >= ORDER_MINIMUM.getAmount() && orders.containsMenuType(MAIN),
            (date, orders) ->  orders.countMenuType(MAIN) * WEEKEND_BENEFIT_UNIT.getAmount()),
    SPECIAL("특별 할인",
            VisitingDate::isSpecial,
            orders -> orders.calculateTotalPrice() >= ORDER_MINIMUM.getAmount(),
            (date, orders) -> SPECIAL_BENEFIT.getAmount()),
    GIVE_AWAY("증정 이벤트",
            date -> true,
            orders -> orders.calculateTotalPrice() >= GIVE_AWAY_MINIMUM.getAmount(),
            (date, orders) -> GIVE_AWAY_BENEFIT.getAmount());

    private final String eventName;
    private final Predicate<VisitingDate> dateCondition;
    private final Predicate<Orders> ordersCondition;
    private final BiFunction<VisitingDate, Orders, Long>  benefitAmount;

    EventDetail(String eventName, Predicate<VisitingDate> dateCondition, Predicate<Orders> ordersCondition,
                BiFunction<VisitingDate, Orders, Long> benefitAmount) {
        this.eventName = eventName;
        this.dateCondition = dateCondition;
        this.ordersCondition = ordersCondition;
        this.benefitAmount = benefitAmount;
    }

    public static List<EventDetail> findEventByCondition(VisitingDate date, Orders orders) {
        return Arrays.stream(EventDetail.values())
                .filter(eventDetail -> eventDetail.dateCondition.test(date) && eventDetail.ordersCondition.test(orders))
                .toList();
    }

    public long calculateBenefitAmount(VisitingDate date, Orders orders) {
        return benefitAmount.apply(date, orders);
    }

    public String getEventName() {
        return eventName;
    }
}