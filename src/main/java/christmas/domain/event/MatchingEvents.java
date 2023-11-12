package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.service.EventFinder;

import java.util.List;

public class MatchingEvents {
    private final VisitingDate date;  //TODO date, orders 필드를 가지는게 괜찮은지 ~ MatchingEvents의 역할을 생각
    private final Orders orders;
    private final List<EventDetail> matchingEvents;

    private MatchingEvents(VisitingDate date, Orders orders, List<EventDetail> matchingEvents) {
        this.date = date;
        this.orders = orders;
        this.matchingEvents = matchingEvents;
    }

    public static MatchingEvents of(VisitingDate date, Orders orders) {
        List<EventDetail> matchingEvents = EventFinder.findMatchingEvents(date, orders);
        return new MatchingEvents(date, orders, matchingEvents);
    }

    public boolean containsGiveAway() {
        return matchingEvents.stream()
                .anyMatch(event -> event.equals(EventDetail.GIVE_AWAY));
    }

    public List<EventDetail> provideMatchingEvents() {
        return List.copyOf(matchingEvents);
    }

    public List<String> provideEventNames() {
        return matchingEvents.stream()
                .map(EventDetail::getEventName)
                .toList();
    }

    public VisitingDate provideDate() {
        return date;
    }

    public Orders provideOrders() {
        return orders;
    }
}
