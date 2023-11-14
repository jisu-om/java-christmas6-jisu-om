package christmas.service;

import christmas.domain.badge.BadgeGenerator;
import christmas.domain.event.BenefitCalculator;
import christmas.domain.event.EventFinder;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.EventDetailDto;
import christmas.dto.MatchingEventsDto;
import christmas.dto.OrdersDto;

import java.util.List;

public class ChristmasManager {
    private final VisitingDate date;
    private final Orders orders;
    private final MatchingEvents events;

    private ChristmasManager(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
        this.events = EventFinder.findMatchingEvents(date, orders);
    }

    public static ChristmasManager of(VisitingDate date, Orders orders) {
        return new ChristmasManager(date, orders);
    }

    public OrdersDto createOrdersDto() {
        return OrdersDto.from(orders);
    }

    public MatchingEventsDto createMatchingEventsDto() {
        BenefitCalculator calculator = initializeBenefitCalculator();
        List<EventDetailDto> eventDetailDtos = createEventDetailDtos();
        long totalBenefitAmount = calculator.calculateTotalBenefitAmount();
        long totalDiscountAmount = calculator.calculateTotalDiscountAmount();

        return new MatchingEventsDto.Builder()
                .events(eventDetailDtos)
                .originalTotalAmount(provideOriginalTotalAmount())
                .containsGiveAway(isContainsGiveAway())
                .totalBenefitAmount(totalBenefitAmount)
                .totalDiscountAmount(totalDiscountAmount)
                .badgeName(BadgeGenerator.findBadgeName(totalBenefitAmount))
                .build();
    }

    private BenefitCalculator initializeBenefitCalculator() {
        return BenefitCalculator.of(date, orders, events);
    }

    private List<EventDetailDto> createEventDetailDtos() {
        return events.provideMatchingEvents().stream()
                .map(event -> EventDetailDto.of(event.getEventName(), event.calculateBenefitAmount(date, orders)))
                .toList();
    }

    private long provideOriginalTotalAmount() {
        return orders.calculateOriginalTotalAmount();
    }

    private boolean isContainsGiveAway() {
        return events.containsGiveAway();
    }
}