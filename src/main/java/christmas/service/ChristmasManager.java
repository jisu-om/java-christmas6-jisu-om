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

    public MatchingEventsDto createMatchingEventsDto() {
        MatchingEvents events = createMatchingEvents();
        List<EventDetailDto> eventDetailDtos = createEventDetailDtos(events);
        long totalBenefitAmount = BenefitCalculator.calculateTotalBenefitAmount(date, orders, events);
        long totalDiscountAmount = BenefitCalculator.calculateTotalDiscountAmount(date, orders, events);

        return new MatchingEventsDto.Builder()
                .events(eventDetailDtos)
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

    private List<EventDetailDto> createEventDetailDtos(MatchingEvents events) {
        return events.provideMatchingEvents().stream()
                .map(event ->
                        EventDetailDto.of(event.getEventName(), event.calculateBenefitAmount(date, orders)))
                .toList();
    }

    private long provideOriginalTotalAmount() {
        return orders.calculateOriginalTotalAmount();
    }
}