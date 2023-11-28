package christmas.controller;

import christmas.domain.EventFinder;
import christmas.domain.event.MatchingEvents;
import christmas.dto.*;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    private MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static MainController create() {
        return new MainController(InputView.getInstance(), OutputView.getInstance());
    }

    public void run() {
        outputView.printStart();
        VisitingDate date = createVisitingDate();
        Orders orders = createOrders();
        outputView.printResultStart();
        OrdersDto ordersDto = OrdersDto.from(orders);
        outputView.printOrderDetail(ordersDto);
        MatchingEvents matchingEvents = EventFinder.findMatchingEvents(date, orders);
        ResultDto resultDto = ResultDto.of(orders, matchingEvents);
        outputView.printResult(resultDto);
    }

    private VisitingDate createVisitingDate() {
        return readUserInput(() -> {
            int input = inputView.readVisitingDate();
            return VisitingDate.from(input);
        });
    }

    private Orders createOrders() {
        return readUserInput(() -> {
            List<OrderItemDto> orderItemDtos = inputView.readOrderItemDtos();
            List<OrderItem> orderItems = orderItemDtos.stream()
                    .map(dto -> OrderItem.of(dto.getName(), dto.getQuantity()))
                    .toList();
            return Orders.from(orderItems);
        });
    }

    private <T> T readUserInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}