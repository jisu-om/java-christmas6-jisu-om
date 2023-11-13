package christmas.controller;

import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.MatchingEventsDto;
import christmas.dto.OrdersDto;
import christmas.service.ChristmasManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private VisitingDateInputHandler visitingDateInputHandler;
    private OrdersInputHandler ordersInputHandler;
    private ChristmasManager christmasManager;

    private ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static ChristmasController of(InputView inputView, OutputView outputView) {
        return new ChristmasController(inputView, outputView);
    }

    public void run() {
        initializeInputHandlers();
        initializeChristmasManager();
        printOrders();
        printResult();
    }

    private void initializeInputHandlers() {
        visitingDateInputHandler = VisitingDateInputHandler.of(inputView, outputView);
        ordersInputHandler = OrdersInputHandler.of(inputView, outputView);
    }

    private void initializeChristmasManager() {
        outputView.printStart();
        VisitingDate date = visitingDateInputHandler.createVisitingDate();
        Orders orders = ordersInputHandler.createOrders();
        christmasManager = ChristmasManager.of(date, orders);
        outputView.printResultStart(date.getDate());
    }

    private void printOrders() {
        OrdersDto ordersDto = christmasManager.createOrdersDto();
        outputView.printMenu(ordersDto);
    }

    private void printResult() {
        MatchingEventsDto matchingEventsDto = christmasManager.createMatchingEventsDto();
        outputView.printOriginalTotalAmount(matchingEventsDto.getOriginalTotalAmount());
        outputView.printGiveAway(matchingEventsDto.isContainsGiveAway());
        outputView.printMatchingEvents(matchingEventsDto.getEvents());
        outputView.printTotalBenefitAmount(matchingEventsDto.getTotalBenefitAmount());
        outputView.printExpectedTotalAmount(matchingEventsDto.getExpectedTotalAmount());
        outputView.printBadge(matchingEventsDto.getBadgeName());
    }
}