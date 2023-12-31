package christmas.domain.event;

import static christmas.domain.event.EventDetail.*;

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
        return eventDetail == GIVE_AWAY;
    }

    public String provideEventName() {
        return eventDetail.getEventName();
    }

    public long provideBenefitAmount() {
        return benefitAmount;
    }
}