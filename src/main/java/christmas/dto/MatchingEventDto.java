package christmas.dto;


public class MatchingEventDto {
    private final String eventName;
    private final long benefitAmount;

    private MatchingEventDto(String eventName, long benefitAmount) {
        this.eventName = eventName;
        this.benefitAmount = benefitAmount;
    }

    public static MatchingEventDto of(String eventName, long benefitAmount) {
        return new MatchingEventDto(eventName, benefitAmount);
    }

    public String getEventName() {
        return eventName;
    }

    public long getBenefitAmount() {
        return benefitAmount;
    }
}