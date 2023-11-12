package christmas.service;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvents;

public class BenefitCalculator {
    public static long calculateTotalBenefitAmount(MatchingEvents matchingEvents) {
        if (matchingEvents.containsGiveAway()) {
            return calculateTotalDiscountAmount(matchingEvents) + EventDetail.getGiveAwayPrice();
        }
        return calculateTotalDiscountAmount(matchingEvents);
    }

    public static long calculateTotalDiscountAmount(MatchingEvents matchingEvents) {
        return matchingEvents.provideMatchingEvents().stream()
                .mapToLong(event -> event.calculateDiscountAmount(matchingEvents.provideDate(), matchingEvents.provideOrders()))
                .sum();
    }
}
