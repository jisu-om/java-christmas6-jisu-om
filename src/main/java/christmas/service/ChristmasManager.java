package christmas.service;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvent;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.OrdersDto;
import christmas.dto.ResultDto;

import java.util.List;

public class ChristmasManager {
    private final VisitingDate date;
    private final Orders orders;

    private ChristmasManager(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public static ChristmasManager of(VisitingDate date, Orders orders) {
        return new ChristmasManager(date, orders);
    }

    public OrdersDto createOrdersDto() {
        return OrdersDto.from(orders);
    }

    public ResultDto createResultDto() {
        MatchingEvents events = createMatchingEvents();
        return ResultDto.of(events, orders);
    }

    private MatchingEvents createMatchingEvents() {
        List<MatchingEvent> eventDetails = EventDetail.findByCondition(date, orders);
        return MatchingEvents.from(eventDetails);
    }
}