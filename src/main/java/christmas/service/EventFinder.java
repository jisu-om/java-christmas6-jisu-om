package christmas.service;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class EventFinder {
    private EventFinder() {
    }

    public static List<EventDetail> findMatchingEvents(VisitingDate date, Orders orders) {
        return EventDetail.findByCondition(date, orders);
    }
}
