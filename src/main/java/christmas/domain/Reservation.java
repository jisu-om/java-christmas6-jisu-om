package christmas.domain;

import christmas.domain.event.EventDetail;
import christmas.dto.MatchingEventDto;
import christmas.dto.MatchingEventsDto;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;

import java.util.List;

public class Reservation {
    private final VisitingDate date;
    private final Orders orders;

    private Reservation(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public static Reservation of(VisitingDate date, Orders orders) {
        return new Reservation(date, orders);
    }

    public MatchingEventsDto createMatchingEvents() {
        List<EventDetail> events = EventDetail.findEventByCondition(date, orders);
        List<MatchingEventDto> matchingEventDtos = events.stream()
                .map(event -> MatchingEventDto.of(event, event.calculateBenefitAmount(date, orders)))
                .toList();
        return MatchingEventsDto.from(matchingEventDtos);
    }
}
