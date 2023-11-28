package christmas.domain.event;

public class MatchingEvent {
    private final EventDetail eventDetail;
    private final long benefitAmount;

    private MatchingEvent(EventDetail eventDetail, long benefitAmount) {
        this.eventDetail = eventDetail;
        this.benefitAmount = benefitAmount;
    }

    public static MatchingEvent of(EventDetail eventDetail, long benefitAmount) {
        return new MatchingEvent(eventDetail, benefitAmount);
    }

    public String getEventName() {
        return eventDetail.getEventName();
    }

    public long getBenefitAmount() {
        return benefitAmount;
    }

    public boolean isEqualEvent(EventDetail event) {
        return eventDetail.isEqual(event);
    }
}
