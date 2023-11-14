package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import static christmas.constants.DiscountConstants.GIVE_AWAY_PRICE;

public class BenefitCalculator {
    private BenefitCalculator() {
    }

    public static long calculateTotalBenefitAmount(VisitingDate date, Orders orders, MatchingEvents events) {
        return events.provideMatchingEvents().stream()
                .mapToLong(event -> event.calculateBenefitAmount(date, orders))
                .sum();
    }

    public static long calculateTotalDiscountAmount(VisitingDate date, Orders orders, MatchingEvents events) {
        if (events.containsGiveAway()) {
            return calculateTotalBenefitAmount(date, orders, events) - GIVE_AWAY_PRICE;
        }
        return calculateTotalBenefitAmount(date, orders, events);
    }
}