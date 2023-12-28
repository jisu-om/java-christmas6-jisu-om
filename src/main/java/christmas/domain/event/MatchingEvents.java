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
        if (containsGiveaway()) {
            int giveawayBenefitAmount = events.stream()
                    .filter(event -> event instanceof GiveawayEvent)
                    .mapToInt(Event::getBenefitAmount)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(INVALID_GIVEAWAY_BENEFIT_AMOUNT.getMessage()));
            return calculateTotalBenefitAmount() - giveawayBenefitAmount;
        }
        return calculateTotalBenefitAmount();
    }

    public boolean containsGiveaway() {
        return events.stream()
                .anyMatch(event -> event instanceof GiveawayEvent);
    }

    public List<Event> getMatchingEvents() {
        return List.copyOf(events);
    }
}
