package christmas.domain.event;

import christmas.domain.badge.BadgeCondition;

import java.util.List;

import static christmas.constants.BenefitConstants.GIVE_AWAY_PRICE;

public class MatchingEvents {
    private final List<MatchingEvent> events;

    private MatchingEvents(List<MatchingEvent> events) {
        this.events = events;
    }

    public static MatchingEvents from(List<MatchingEvent> events) {
        return new MatchingEvents(events);
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

    public long calculateTotalBenefitAmount() {
        return events.stream()
                .mapToLong(MatchingEvent::provideBenefitAmount)
                .sum();
    }

    public List<MatchingEvent> provideMatchingEvents() {
        return List.copyOf(events);
    }

    public String findBadgeName() {
        return findBadge().getBadgeName();
    }

    private BadgeCondition findBadge() {
        return BadgeCondition.findBadgeByCondition(calculateTotalBenefitAmount());
    }
}