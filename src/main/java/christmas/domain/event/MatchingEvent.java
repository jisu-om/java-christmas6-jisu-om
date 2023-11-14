package christmas.domain.event;

public class MatchingEvent {
    private final EventDetail eventDetail;
    private final long benefitAmount;

    private MatchingEvent(EventDetail eventDetail, long benefitAmount) {
        this.eventDetail = eventDetail;
        this.benefitAmount = benefitAmount;
    }

    protected static MatchingEvent of(EventDetail eventDetail, long benefitAmount) {
        return new MatchingEvent(eventDetail, benefitAmount);
    }

    public boolean isGiveAway() {
        return eventDetail == EventDetail.GIVE_AWAY;
    }

    public String getEventName() {
        return eventDetail.getEventName();
    }

    public long getBenefitAmount() {
        return benefitAmount;
    }
}