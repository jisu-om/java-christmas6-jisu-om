package christmas.dto;

import christmas.domain.event.EventDetail;

import java.util.List;

public class ResultDto {
    private final long originalTotalPrice;
    private final List<EventDetail> events;
    private long expectedPaymentAmount;
    private boolean containsGiveAway;
    private long totalBenefitAmount;
    private String badgeName;

    private ResultDto(long originalTotalPrice, List<EventDetail> events) {
        this.originalTotalPrice = originalTotalPrice;
        this.events = events;
    }

    public static ResultDto of(long originalTotalPrice, List<EventDetail> events) {
        return new ResultDto(originalTotalPrice, events);
    }

    public void setExpectedPaymentAmount(long expectedPaymentAmount) {
        this.expectedPaymentAmount = expectedPaymentAmount;
    }

    public void setContainsGiveAway(boolean containsGiveAway) {
        this.containsGiveAway = containsGiveAway;
    }

    public void setTotalBenefitAmount(long totalBenefitAmount) {
        this.totalBenefitAmount = totalBenefitAmount;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public long getOriginalTotalPrice() {
        return originalTotalPrice;
    }

    public List<EventDetail> getEvents() {
        return events;
    }

    public long getExpectedPaymentAmount() {
        return expectedPaymentAmount;
    }

    public boolean isContainsGiveAway() {
        return containsGiveAway;
    }

    public long getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
