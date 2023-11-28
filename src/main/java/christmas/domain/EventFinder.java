package christmas.domain;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvent;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class EventFinder {
    private EventFinder() {
    }
    public static MatchingEvents findMatchingEvents(VisitingDate date, Orders orders) {
        List<EventDetail> events = EventDetail.findEventByCondition(date, orders);
        List<MatchingEvent> matchingEvents = events.stream()
                .map(event -> MatchingEvent.of(event, event.calculateBenefitAmount(date, orders)))
                .toList();
        return MatchingEvents.from(matchingEvents);
    }
}