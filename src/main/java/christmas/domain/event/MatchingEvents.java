package christmas.domain.event;

import java.util.List;

public class MatchingEvents {
    private final List<EventDetail> events;

    private MatchingEvents(List<EventDetail> events) {
        this.events = events;
    }

    public static MatchingEvents from(List<EventDetail> eventDetails) {
        return new MatchingEvents(eventDetails);
    }

    public boolean containsGiveAway() {
        return events.stream()
                .anyMatch(event -> event.equals(EventDetail.GIVE_AWAY));
    }

    public List<EventDetail> provideMatchingEvents() {
        return List.copyOf(events);
    }
}