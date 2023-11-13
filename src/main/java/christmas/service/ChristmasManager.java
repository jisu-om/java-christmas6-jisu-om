package christmas.service;

import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.ResultDto;

public class ChristmasManager {

    private ChristmasManager(VisitingDate date, Orders orders) {

    }

    public static ChristmasManager of(VisitingDate date, Orders orders) {
        return new ChristmasManager(date, orders);
    }

    public ResultDto createResultDto() {
//        MatchingEvents matchingEvents = MatchingEvents.of(date, orders);
//        return ResultDto.of(matchingEvents, orders);
        return null;
    }

    private String provideBadgeName(MatchingEvents events) {
        return BadgeGenerator.findBadgeName(events);
    }
}
