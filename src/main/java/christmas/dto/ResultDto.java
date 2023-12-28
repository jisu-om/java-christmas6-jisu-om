package christmas.dto;

import christmas.domain.Reservation;
import christmas.domain.badge.Badge;
import christmas.domain.badge.BadgeFinder;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.MatchingEvents;

public record ResultDto(int originalPrice, boolean containsGiveaway, String giveawayItemName,
                        EventsDto eventsDto, int totalBenefitAmount, int finalPrice, BadgeDto badgeDto) {

    public static ResultDto from(Reservation reservation) {
        int originalPrice = reservation.getOriginalPrice();
        MatchingEvents matchingEvents = reservation.findMatchingEvents();
        boolean containsGiveaway = matchingEvents.containsGiveaway();
        String giveawayItemName = GiveawayEvent.getGiveawayItemName();
        EventsDto eventsDto = EventsDto.from(matchingEvents);
        int totalBenefitAmount = matchingEvents.calculateTotalBenefitAmount();
        int finalPrice = reservation.calculateFinalPrice();
        Badge badge = BadgeFinder.find(totalBenefitAmount);
        BadgeDto badgeDto = BadgeDto.from(badge);

        return new ResultDto(originalPrice, containsGiveaway, giveawayItemName,
                eventsDto, totalBenefitAmount, finalPrice, badgeDto);
    }
}
