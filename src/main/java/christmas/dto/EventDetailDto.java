package christmas.dto;

public class EventDetailDto {
    private final String eventName;
    private final long benefitAmount;

    private EventDetailDto(String eventName, long benefitAmount) {
        this.eventName = eventName;
        this.benefitAmount = benefitAmount;
    }

    public static EventDetailDto of(String eventName, long benefitAmount) {
        return new EventDetailDto(eventName, benefitAmount);
    }

    public String getEventName() {
        return eventName;
    }

    public long getBenefitAmount() {
        return benefitAmount;
    }
}