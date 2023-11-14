package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class EventFinder {
    private EventFinder() {
    }

    public static MatchingEvents findMatchingEvents(VisitingDate date, Orders orders) {
        List<MatchingEvent> eventDetails = EventDetail.findByCondition(date, orders);
        return MatchingEvents.from(eventDetails);
    }
}