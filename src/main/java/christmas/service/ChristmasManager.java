package christmas.service;

import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.MatchingEventsDto;
import christmas.dto.OrdersDto;

public class ChristmasManager {
    private final MatchingEvents events;

    private ChristmasManager(VisitingDate date, Orders orders) {
        events = MatchingEvents.of(date, orders);
    }

    public static ChristmasManager of(VisitingDate date, Orders orders) {
        return new ChristmasManager(date, orders);
    }

    public OrdersDto createOrdersDto() {
        return OrdersDto.from(events.provideOrders());
    }

    public MatchingEventsDto createMatchingEventsDto() {
        return MatchingEventsDto.of(events);
    }
}