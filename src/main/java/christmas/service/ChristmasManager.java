package christmas.service;

import christmas.domain.badge.BadgeGenerator;
import christmas.domain.event.BenefitCalculator;
import christmas.domain.event.EventFinder;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.domain.event.MatchingEvent;
import christmas.dto.ResultDto;
import christmas.dto.OrdersDto;

import java.util.List;

public class ChristmasManager {
    private final VisitingDate date;
    private final Orders orders;

    private ChristmasManager(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public static ChristmasManager of(VisitingDate date, Orders orders) {
        return new ChristmasManager(date, orders);
    }

    public OrdersDto createOrdersDto() {
        return OrdersDto.from(orders);
    }

    public ResultDto createResultDto() {
        MatchingEvents events = createMatchingEvents();
        long totalBenefitAmount = events.calculateTotalBenefitAmount();
        long totalDiscountAmount = events.calculateTotalDiscountAmount();

        return new ResultDto.Builder()
                .events(events.provideMatchingEvents())
                .originalTotalAmount(provideOriginalTotalAmount())
                .containsGiveAway(events.containsGiveAway())
                .totalBenefitAmount(totalBenefitAmount)
                .totalDiscountAmount(totalDiscountAmount)
                .badgeName(BadgeGenerator.findBadgeName(totalBenefitAmount))
                .build();
    }

    private MatchingEvents createMatchingEvents() {
        return EventFinder.findMatchingEvents(date, orders);
    }

    private long provideOriginalTotalAmount() {
        return orders.calculateOriginalTotalAmount();
    }
}