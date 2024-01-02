package christmas.dto;

import christmas.domain.event.MatchingEvents;

import java.util.List;

public record EventsDto(List<EventDto> eventDtos) {
    public static EventsDto from(MatchingEvents events) {
        return new EventsDto(events.getMatchingEvents().stream()
                .map(EventDto::from)
                .toList());
    }
}
