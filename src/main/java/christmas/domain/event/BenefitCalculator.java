package christmas.domain.event;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import static christmas.constants.DiscountConstants.GIVE_AWAY_PRICE;

public class BenefitCalculator {
    private final VisitingDate date;
    private final Orders orders;
    private final MatchingEvents events;

    private BenefitCalculator(VisitingDate date, Orders orders, MatchingEvents events) {
        this.date = date;
        this.orders = orders;
        this.events = events;
    }

    public static BenefitCalculator of(VisitingDate date, Orders orders, MatchingEvents events) {
        return new BenefitCalculator(date, orders, events);
    }

    public long calculateTotalBenefitAmount() {
        return events.provideMatchingEvents().stream()
                .mapToLong(event -> event.calculateBenefitAmount(date, orders))
                .sum();
    }

    public long calculateTotalDiscountAmount() {
        if (events.containsGiveAway()) {
            return calculateTotalBenefitAmount() - GIVE_AWAY_PRICE;
        }
        return calculateTotalBenefitAmount();
    }
}