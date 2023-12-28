package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.MatchingEvents;
import christmas.domain.event.discount.ChristmasDiscount;
import christmas.domain.event.discount.SpecialDiscount;
import christmas.domain.event.discount.WeekdayDiscount;
import christmas.domain.event.discount.WeekendDiscount;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.Arrays;
import java.util.List;

public class Reservation {
    private final VisitingDate date;
    private final Orders orders;

    public Reservation(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public int calculateFinalPrice() {
        return orders.calculateTotalPrice() - findMatchingEvents().calculateTotalDiscountAmount();
    }

    public MatchingEvents findMatchingEvents() {
        List<Event> allEvents = Arrays.asList(new ChristmasDiscount(date), new WeekdayDiscount(date, orders),
                new WeekendDiscount(date, orders), new SpecialDiscount(date), new GiveawayEvent(orders));

        List<Event> events = allEvents.stream()
                .filter(Event::isApplied)
                .toList();

        return new MatchingEvents(events);
    }

    public int getOriginalPrice() {
        return orders.calculateTotalPrice();
    }
}
