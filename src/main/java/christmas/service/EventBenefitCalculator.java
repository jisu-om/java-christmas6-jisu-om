package christmas.service;

import christmas.domain.event.EventDetail;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class EventBenefitCalculator {
    private final VisitingDate date;
    private final Orders orders;
    private final List<EventDetail> events;

    private EventBenefitCalculator(List<EventDetail> events, VisitingDate date, Orders orders) {
        this.events = events;
        this.date = date;
        this.orders = orders;
    }

    public long calculateDiscountAmount() {
        return events.stream()
                .mapToLong(event -> event.calculateDiscountAmount(date, orders))
                .sum();
    }

    public long calculateTotalBenefitAmount(boolean containsGiveAway) {
        if (containsGiveAway) {
            return calculateDiscountAmount() + EventDetail.getGiveAwayPrice();
        }
        return calculateDiscountAmount();
    }
}
