package christmas.service;

import christmas.domain.Orders;
import christmas.domain.VisitingDate;

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

    public Events provideEvents() {
        //TODO date, orders 와 EventCondition 을 비교하여 Events 객체 생성
        return null;
    }
}
