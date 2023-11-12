package christmas.service;

import christmas.domain.event.EventDetail;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class EventService {
    private final VisitingDate date;
    private final Orders orders;

    private EventService(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public static EventService of(VisitingDate date, Orders orders) {
        return new EventService(date, orders);
    }

    public List<EventDetail> findMatchingEvents() {
        //TODO date, orders 와 EventDetail 을 비교하여 Event(혜택내역) 객체 생성 or List<EventDetail> 을 리턴하도록 할까?
        return EventDetail.findByDateAndPriceCondition(date, orders.calculateOriginalPrice());
    }
}
