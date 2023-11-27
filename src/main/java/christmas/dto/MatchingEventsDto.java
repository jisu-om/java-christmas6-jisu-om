package christmas.dto;


import christmas.domain.event.MatchingEvents;

import java.util.List;

public class MatchingEventsDto {
    private final List<MatchingEventDto> events;

    private MatchingEventsDto(List<MatchingEventDto> events) {
        this.events = events;
    }

    public static MatchingEventsDto from(MatchingEvents matchingEvents) {
        List<MatchingEventDto> eventDtos = matchingEvents.getEvents().stream()
                .map(event -> MatchingEventDto.of(event.getEventName(), event.getBenefitAmount()))
                .toList();
        return new MatchingEventsDto(eventDtos);
    }

    public List<MatchingEventDto> getEvents() {
        return events;
    }
}
