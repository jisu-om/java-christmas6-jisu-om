package christmas.service;

import christmas.domain.event.EventDetail;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class EventFinder {
    private final VisitingDate date;
    private final Orders orders;
    private final List<EventDetail> events;

    private EventFinder(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
        this.events = findMatchingEvents();
    }

    public static EventFinder of(VisitingDate date, Orders orders) {
        return new EventFinder(date, orders);
    }

    public List<EventDetail> findMatchingEvents() {
        return EventDetail.findByCondition(date, orders);
    }

    public List<String> provideEventNames() {
        return events.stream()
                .map(EventDetail::getEventName)
                .toList();
    }

    public boolean containsGiveAway() {
        return events.stream()
                .anyMatch(event -> event.equals(EventDetail.GIVE_AWAY));
    }
}
