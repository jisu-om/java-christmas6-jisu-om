package christmas.dto;

import christmas.domain.event.MatchingEvent;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;

import java.util.List;

public class ResultDto {
    private final List<MatchingEventDto> events;
    private final long originalTotalAmount;
    private final boolean containsGiveAway;
    private final long totalBenefitAmount;
    private final long totalDiscountAmount;
    private final String badgeName;

    private ResultDto(MatchingEvents events, Orders orders) {
        this.events = events.provideMatchingEvents().stream()
                .map(MatchingEventDto::of)
                .toList();
        this.originalTotalAmount = orders.calculateOriginalTotalAmount();
        this.containsGiveAway = events.containsGiveAway();
        this.totalBenefitAmount = events.calculateTotalBenefitAmount();
        this.totalDiscountAmount = events.calculateTotalDiscountAmount();
        this.badgeName = events.findBadgeName();
    }

    public static ResultDto of(MatchingEvents events, Orders orders) {
        return new ResultDto(events, orders);
    }

    public List<MatchingEventDto> getEventDetails() {
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