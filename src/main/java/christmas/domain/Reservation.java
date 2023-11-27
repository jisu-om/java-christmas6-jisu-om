package christmas.domain;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvent;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class Reservation {
    private final VisitingDate date;
    private final Orders orders;

    private Reservation(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public static Reservation of(VisitingDate date, Orders orders) {
        return new Reservation(date, orders);
    }


    public MatchingEvents createMatchingEvents() {
        List<EventDetail> events = EventDetail.findEventByCondition(date, orders);
        List<MatchingEvent> matchingEvents = events.stream()
                .map(event -> MatchingEvent.of(event, event.calculateBenefitAmount(date, orders)))
                .toList();
        return MatchingEvents.from(matchingEvents);
    }
}
