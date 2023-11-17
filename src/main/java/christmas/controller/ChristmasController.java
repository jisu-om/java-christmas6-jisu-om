package christmas.controller;

import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.OrderItemDto;
import christmas.dto.OrdersDto;
import christmas.dto.ResultDto;
import christmas.service.ChristmasManager;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private ChristmasManager christmasManager;

    private ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static ChristmasController of(InputView inputView, OutputView outputView) {
        return new ChristmasController(inputView, outputView);
    }

    public void run() {
        initializeChristmasManager();
        printOrders();
        printResult();
    }

    private void initializeChristmasManager() {
        outputView.printStart();
        VisitingDate date = createVisitingDate();
        Orders orders = createOrders();
        christmasManager = ChristmasManager.of(date, orders);
        outputView.printResultStart(date.provideDate());
    }

    private VisitingDate createVisitingDate() {
        return readUserInput(() -> {
            int input = inputView.readVisitingDate();
            return VisitingDate.from(input);
        });
    }

    private Orders createOrders() {
        return readUserInput(() -> {
            List<OrderItem> items = inputView.readOrders().stream()
                    .map(OrderItemDto::toOrderItem)
                    .toList();
            return Orders.from(items);
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

    private void printOrders() {
        OrdersDto ordersDto = christmasManager.createOrdersDto();
        outputView.printMenu(ordersDto);
    }

    private void printResult() {
        ResultDto resultDto = christmasManager.createResultDto();
        outputView.printResult(resultDto);
    }
}