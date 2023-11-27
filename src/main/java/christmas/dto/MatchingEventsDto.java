package christmas.dto;


import java.util.List;

public class MatchingEventsDto {
    private final List<MatchingEventDto> events;

    private MatchingEventsDto(List<MatchingEventDto> events) {
        this.events = events;
    }

    public static MatchingEventsDto from(List<MatchingEventDto> events) {
        return new MatchingEventsDto(events);
    }

    public List<MatchingEventDto> getEvents() {
        return events;
    }

    public boolean isNone() {
        return events.stream()
                .anyMatch(MatchingEventDto::isEventNone);
    }
}
