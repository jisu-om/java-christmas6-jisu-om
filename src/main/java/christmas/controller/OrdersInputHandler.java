package christmas.controller;

import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.dto.OrderItemDto;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class OrdersInputHandler {
    private final InputView inputView;
    private final OutputView outputView;

    private OrdersInputHandler(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static OrdersInputHandler of(InputView inputView, OutputView outputView) {
        return new OrdersInputHandler(inputView, outputView);
    }

    public Orders createOrders() {
        while (true) {
            try {
                List<OrderItem> items = inputView.readOrders().stream()
                        .map(OrderItemDto::toOrderItem)
                        .toList();
                return Orders.from(items);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
