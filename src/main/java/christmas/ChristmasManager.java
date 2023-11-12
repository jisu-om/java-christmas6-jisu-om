package christmas;

import christmas.domain.event.EventDetail;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.EventsDto;
import christmas.dto.OrdersDto;
import christmas.dto.PriceDetailDto;
import christmas.service.EventFinder;

import java.util.List;

public class ChristmasManager {
    private final VisitingDate date;
    private final Orders orders;
    private List<EventDetail> events;
    private EventFinder eventFinder;

    private ChristmasManager(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
        initializeEventFinder();
        loadEvents();
    }

    public static ChristmasManager of(VisitingDate date, Orders orders) {
        return new ChristmasManager(date, orders);
    }

    private void initializeEventFinder() {
        eventFinder = EventFinder.of(date, orders);
    }

    private void loadEvents() {
        events = eventFinder.findMatchingEvents();
    }

    public EventsDto createEventsDto() {

        return null;
    }

    public PriceDetailDto createPriceDetailDto() {

        return null;
    }

}
