package christmas.dto;

import christmas.domain.event.EventDetail;

public class MatchingEventDto {
    private final EventDetail eventDetail;
    private final long benefitAmount;

    private MatchingEventDto(EventDetail eventDetail, long benefitAmount) {
        this.eventDetail = eventDetail;
        this.benefitAmount = benefitAmount;
    }

    public static MatchingEventDto of(EventDetail eventDetail, long benefitAmount) {
        return new MatchingEventDto(eventDetail, benefitAmount);
    }

    public String getEventName() {
        return eventDetail.getEventName();
    }

    public long getBenefitAmount() {
        return benefitAmount;
    }
}
