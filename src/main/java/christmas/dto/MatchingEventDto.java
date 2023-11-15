package christmas.dto;

import christmas.domain.event.MatchingEvent;

public class MatchingEventDto {
    private final String eventName;
    private final long benefitAmount;

    private MatchingEventDto(String eventName, long benefitAmount) {
        this.eventName = eventName;
        this.benefitAmount = benefitAmount;
    }

    public static MatchingEventDto of(MatchingEvent event) {
        return new MatchingEventDto(event.provideEventName(), event.provideBenefitAmount());
    }

    public String getEventName() {
        return eventName;
    }

    public long getBenefitAmount() {
        return benefitAmount;
    }
}
