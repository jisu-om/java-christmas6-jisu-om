package christmas;

import christmas.domain.event.EventDetail;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.ResultDto;
import christmas.dto.OrdersDto;
import christmas.service.BadgeGenerator;
import christmas.service.EventService;

import java.util.List;

public class ChristmasManager {
    private final VisitingDate date;
    private final Orders orders;
    private EventService eventService;

    private ChristmasManager(VisitingDate date, Orders orders) {
        this.date = date;
        this.orders = orders;
        initializeEventService();
    }

    public static ChristmasManager of(VisitingDate date, Orders orders) {
        return new ChristmasManager(date, orders);
    }

    private void initializeEventService() {
        eventService = EventService.of(date, orders);
    }

    public OrdersDto createOrdersDto() {
        return OrdersDto.of(orders);
    }

    public ResultDto createResultDto() {
        //TODO ResultService 에게 맡길까??
        long originalTotalPrice = orders.calculateOriginalTotalPrice();
        List<EventDetail> events = eventService.provideEvents();
        ResultDto resultDto = ResultDto.of(originalTotalPrice, events);
        long totalBenefitAmount = eventService.calculateTotalBenefitAmount();
        resultDto.setTotalBenefitAmount(totalBenefitAmount);
        resultDto.setContainsGiveAway(eventService.containsGiveAway());
        long expectedPaymentAmount = originalTotalPrice - eventService.calculateTotalDiscountAmount();
        resultDto.setExpectedPaymentAmount(expectedPaymentAmount);

        String badgeName = BadgeGenerator.findBadgeName(totalBenefitAmount);
        resultDto.setBadgeName(badgeName);
        return resultDto;
    }
}
