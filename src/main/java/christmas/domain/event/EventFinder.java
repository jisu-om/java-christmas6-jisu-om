package christmas.domain.event;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class EventFinder {
    private EventFinder() {
    }

    public static MatchingEvents findMatchingEvents(VisitingDate date, Orders orders) {
        List<EventDetail> eventDetails = EventDetail.findByCondition(date, orders);
        return MatchingEvents.from(eventDetails);
    }
}