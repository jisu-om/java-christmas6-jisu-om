package christmas.domain.event;


import christmas.domain.Badge;

import java.util.List;

import static christmas.domain.event.EventDetail.GIVE_AWAY;

public class MatchingEvents {
    private final List<MatchingEvent> matchingEvents;

    private MatchingEvents(List<MatchingEvent> matchingEvents) {
        this.matchingEvents = matchingEvents;
    }

    public static MatchingEvents from(List<MatchingEvent> events) {
        return new MatchingEvents(events);
    }

    public List<MatchingEvent> getEvents() {
        return List.copyOf(matchingEvents);
    }

    public boolean containsGiveAway() {
        return matchingEvents.stream()
                .anyMatch(event -> event.isEqualEvent(GIVE_AWAY));
    }

    public String findBadgeName() {
        return findBadge().getBadgeName();
    }

    private Badge findBadge() {
        long totalBenefitAmount = calculateTotalBenefitAmount();
        return Badge.findBadgeByCondition(totalBenefitAmount);
    }

    public long calculateTotalBenefitAmount() {
        return matchingEvents.stream()
                .mapToLong(MatchingEvent::getBenefitAmount)
                .sum();
    }
}
