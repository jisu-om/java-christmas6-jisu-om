package christmas;

import christmas.domain.event.EventDetail;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.ResultDto;
import christmas.service.EventFinder;

import java.util.List;

public class ChristmasEventManager {
    private final VisitingDate date;
    private final Orders orders;
    private List<EventDetail> events;
    private final EventFinder eventFinder;

    public ChristmasEventManager(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
        eventFinder = initializeEventFinder();
    }

    private EventFinder initializeEventFinder() {
        return EventFinder.of(date, orders);
    }

    private void loadEvents() {
        events = eventFinder.findMatchingEvents();
    }

    public ResultDto createResultDto() {
        return null;
    }

}
