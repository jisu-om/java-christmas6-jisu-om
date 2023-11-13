package christmas.service;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvents;

public class BenefitCalculator {
    public static long calculateTotalBenefitAmount(MatchingEvents matchingEvents) {
        return matchingEvents.provideMatchingEvents().stream()
                .mapToLong(event -> event.calculateBenefitAmount(matchingEvents.provideDate(), matchingEvents.provideOrders()))
                .sum();
    }

    public static long calculateTotalDiscountAmount(MatchingEvents matchingEvents) {
        if (matchingEvents.containsGiveAway()) {
            return calculateTotalBenefitAmount(matchingEvents) - EventDetail.getGiveAwayPrice();
        }
        return calculateTotalBenefitAmount(matchingEvents);
    }
}
