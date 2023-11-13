package christmas.dto;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvents;
import christmas.service.BadgeGenerator;

import java.util.List;

public class MatchingEventsDto {
    private final List<EventDetailDto> events;
    private long originalTotalAmount;
    private boolean containsGiveAway;
    private long totalBenefitAmount;
    private long totalDiscountAmount;
    private String badgeName;

    private MatchingEventsDto(List<EventDetailDto> eventDetailDtos) {
        this.events = eventDetailDtos;
    }

    public static MatchingEventsDto of(MatchingEvents events) {
        List<EventDetail> eventDetails = events.provideMatchingEvents();
        List<EventDetailDto> eventDetailDtos = eventDetails.stream()
                .map(event -> EventDetailDto.of(event.getEventName(),
                        event.calculateBenefitAmount(events.provideDate(), events.provideOrders())))
                .toList();
        MatchingEventsDto matchingEventsDto = new MatchingEventsDto(eventDetailDtos);
        matchingEventsDto.originalTotalAmount = events.provideOrders().calculateOriginalTotalAmount();
        matchingEventsDto.containsGiveAway = events.containsGiveAway();
        matchingEventsDto.totalBenefitAmount = events.calculateTotalBenefitAmount();
        matchingEventsDto.totalDiscountAmount = events.calculateTotalDiscountAmount();
        matchingEventsDto.badgeName = BadgeGenerator.findBadgeName(events);
        return matchingEventsDto;
    }

    public List<EventDetailDto> getEvents() {
        return events;
    }

    public long getOriginalTotalAmount() {
        return originalTotalAmount;
    }

    public boolean isContainsGiveAway() {
        return containsGiveAway;
    }

    public long getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public long getExpectedTotalAmount() {
        return originalTotalAmount - totalDiscountAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
