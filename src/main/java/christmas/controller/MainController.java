package christmas.controller;

import christmas.domain.OrderItem;
import christmas.domain.Orders;
import christmas.domain.VisitingDate;
import christmas.dto.OrderItemDto;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    //private OrderController orderController;  //컨트롤러 추가하는 경우

    private MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static MainController create() {
        return new MainController(InputView.getInstance(), OutputView.getInstance());
    }

    public void run() {
        outputView.printStart();
        VisitingDate visitingDate = createVisitingDate();
        Orders orders = createOrders();

    }

    private void initializeControllers() {
        orderController = OrderController.of(inputView, outputView);
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
