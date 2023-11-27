package christmas.dto;

import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;

import static christmas.constants.BenefitConstants.GIVE_AWAY_BENEFIT_AMOUNT;

public class ResultDto {
    private final long originalAmount;
    private final boolean containsGiveAway;
    private final MatchingEventsDto matchingEventsDto;
    private final long totalBenefitAmount;
    private final long finalAmount;
    private final String badgeName;

    private ResultDto(long originalAmount, boolean isGiveAway, MatchingEventsDto matchingEventsDto,
                      long totalBenefitAmount, long finalAmount, String badgeName)
    {
        this.originalAmount = originalAmount;
        this.containsGiveAway = isGiveAway;
        this.matchingEventsDto = matchingEventsDto;
        this.totalBenefitAmount = totalBenefitAmount;
        this.finalAmount = finalAmount;
        this.badgeName = badgeName;
    }

    public static ResultDto of(Orders orders, MatchingEvents matchingEvents) {
        long originalAmount = orders.calculateTotalPrice();
        boolean containsGiveAway = matchingEvents.containsGiveAway();
        MatchingEventsDto matchingEventsDto = MatchingEventsDto.from(matchingEvents);
        long totalBenefitAmount = matchingEvents.calculateTotalBenefitAmount();
        long discountAmount = totalBenefitAmount;
        if (containsGiveAway) {
            discountAmount = totalBenefitAmount - GIVE_AWAY_BENEFIT_AMOUNT;
        }
        long finalAmount = originalAmount - discountAmount;
        String badgeName = matchingEvents.findBadgeName();
        return new ResultDto(originalAmount, containsGiveAway, matchingEventsDto, totalBenefitAmount, finalAmount, badgeName);
    }

    public long getOriginalAmount() {
        return originalAmount;
    }

    public boolean isContainsGiveAway() {
        return containsGiveAway;
    }

    public MatchingEventsDto getMatchingEventsDto() {
        return matchingEventsDto;
    }

    public long getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public long getFinalAmount() {
        return finalAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
