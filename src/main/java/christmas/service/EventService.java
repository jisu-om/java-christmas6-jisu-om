package christmas.service;

import christmas.domain.event.EventDetail;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class EventService {
    private final VisitingDate date;
    private final Orders orders;
    private final List<EventDetail> events;

    private EventService(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
        this.events = findMatchingEvents();
    }

    public static EventService of(VisitingDate date, Orders orders) {
        return new EventService(date, orders);
    }

    private List<EventDetail> findMatchingEvents() {
        return EventDetail.findByCondition(date, orders);
    }

    public List<String> provideEventNames() {
        return events.stream()
                .map(EventDetail::getEventName)
                .toList();
    }

    public long calculateDiscountAmount() {
        return events.stream()
                .mapToLong(event -> event.calculateDiscountAmount(date, orders))
                .sum();
    }

    public boolean isGiveAwayMatched() {
        return events.stream()
                .anyMatch(event -> event.equals(EventDetail.GIVE_AWAY));
    }

    public long calculateTotalBenefitAmount() {
        if (isGiveAwayMatched()) {
            return calculateDiscountAmount() + EventDetail.getGiveAwayPrice();
        }
        return calculateDiscountAmount();
    }
}
