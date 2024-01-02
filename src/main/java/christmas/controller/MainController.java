package christmas.controller;

import christmas.domain.Reservation;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.OrderItemDto;
import christmas.dto.OrdersDto;
import christmas.dto.ResultDto;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStart();

        VisitingDate date = createVisitingDate();
        Orders orders = createOrders();
        outputView.printResultStart();

        OrdersDto ordersDto = OrdersDto.from(orders);
        outputView.printOrderDetail(ordersDto);

        processResult(date, orders);
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
                    .map(dto -> OrderItem.of(dto.name(), dto.quantity()))
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

    private void processResult(VisitingDate date, Orders orders) {
        Reservation reservation = new Reservation(date, orders);
        ResultDto resultDto = ResultDto.from(reservation);
        outputView.printResult(resultDto);
    }
}