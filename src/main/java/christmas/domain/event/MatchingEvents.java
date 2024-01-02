package christmas.domain.event;


import java.util.List;

import static christmas.exception.ErrorMessage.*;

public class MatchingEvents {
    private final List<Event> events;

    public MatchingEvents(List<Event> events) {
        this.events = events;
    }

    public int calculateTotalBenefitAmount() {
        return events.stream()
                .mapToInt(Event::getBenefitAmount)
                .sum();
    }

    public int calculateTotalDiscountAmount() {
        return events.stream()
                .filter(Event::isDiscount)
                .mapToInt(Event::getBenefitAmount)
                .sum();
    }

    public boolean containsGiveaway() {
        return events.stream()
                .anyMatch(Event::isGiveaway);
    }

    public List<Event> getMatchingEvents() {
        return List.copyOf(events);
    }
}
