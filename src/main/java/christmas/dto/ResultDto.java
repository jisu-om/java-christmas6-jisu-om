package christmas.dto;

import christmas.domain.Reservation;
import christmas.domain.badge.Badge;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.MatchingEvents;

public record ResultDto(int originalPrice, boolean containsGiveaway, String giveawayItemName,
                        int totalBenefitAmount, int finalPrice, String badgeName) {

    public static ResultDto from(Reservation reservation) {
        int originalPrice = reservation.getOriginalPrice();
        MatchingEvents matchingEvents = reservation.findMatchingEvents();
        boolean containsGiveaway = matchingEvents.containsGiveaway();
        String giveawayItemName = GiveawayEvent.getGiveawayItemName();
        int totalBenefitAmount = matchingEvents.calculateTotalBenefitAmount();
        int finalPrice = reservation.calculateFinalPrice();
        Badge badge = Badge.findBadgeByCondition(totalBenefitAmount);
        String badgeName = badge.getBadgeName();

        return new ResultDto(originalPrice, containsGiveaway, giveawayItemName, totalBenefitAmount, finalPrice, badgeName);
    }
}
