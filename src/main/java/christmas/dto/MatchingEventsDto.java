package christmas.dto;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvents;

import java.util.List;

public class MatchingEventsDto {
    private List<EventDetailDto> events;

    private MatchingEventsDto(List<EventDetailDto> eventDetailDtos) {
        this.events = eventDetailDtos;
    }

    public static MatchingEventsDto of(MatchingEvents events) {
        List<EventDetail> eventDetails = events.provideMatchingEvents();
        List<EventDetailDto> eventDetailDtos = eventDetails.stream()
                .map(event -> EventDetailDto.of(event.getEventName(),
                        event.calculateBenefitAmount(events.provideDate(), events.provideOrders())))
                .toList();
        return new MatchingEventsDto(eventDetailDtos);
    }

    public List<EventDetailDto> getEvents() {
        return events;
    }
}
