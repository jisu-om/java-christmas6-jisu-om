package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

import static christmas.constants.DiscountConstants.GIVE_AWAY_PRICE;

public class MatchingEvents {
    private final List<MatchingEvent> events;

    private MatchingEvents(List<MatchingEvent> events) {
        this.events = events;
    }

    public static MatchingEvents from(List<MatchingEvent> eventDetails) {
        return new MatchingEvents(eventDetails);
    }

    public long calculateTotalBenefitAmount() {
        return events.stream()
                .mapToLong(MatchingEvent::provideBenefitAmount)
                .sum();
    }

    public long calculateTotalDiscountAmount() {
        if (containsGiveAway()) {
            return calculateTotalBenefitAmount() - GIVE_AWAY_PRICE;
        }
        return calculateTotalBenefitAmount();
    }

    public boolean containsGiveAway() {
        return events.stream().anyMatch(MatchingEvent::isGiveAway);
    }

    public List<MatchingEvent> provideMatchingEvents() {
        return List.copyOf(events);
    }
}