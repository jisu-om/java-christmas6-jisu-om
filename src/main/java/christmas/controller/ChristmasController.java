package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.OrderItemDto;
import christmas.dto.OrdersDto;
import christmas.dto.ResultDto;
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
        outputView.printStart();
        //TODO readVisitingDate() : visitingDateInputHandler 를 통해 VisitingDate 생성 (예외 발생 시 다시 받기)
        //TODO readOrders() : ordersInputHandler 를 통해 Orders 생성 (예외 발생 시 다시 받기)
        VisitingDate date = visitingDateInputHandler.createVisitingDate();
        Orders orders = ordersInputHandler.createOrders();

        MatchingEvents matchingEvents = MatchingEvents.of(date, orders);
        //TODO visitingDate, orders 로 ChristmasManager 생성
//        christmasManager = ChristmasManager.of(date, orders);

        outputView.printResultStart();
        //TODO Orders 를 바탕으로 OrdersDto 생성해서 outputView 에 전달 (주문 메뉴 출력)
        OrdersDto ordersDto = OrdersDto.from(orders);
        outputView.printMenu(ordersDto);

        //TODO christmasManager 에게 할인전 총주문금액, 혜택내역, 할인금액, 총혜택금액, 할인후 예상결제금액보, 배지정보 계산 후
        // ResultDto 를 생성하라고 요청
        // (따로따로 객체 생성? ex. PricingInfo - 이들이 객체로서 하는 로직이 있는 경우에 생성)
        //TODO resultDto 이 자체가 아니라 필요한 정보들만 outputView 에 넘겨줌
//        ResultDto resultDto = christmasManager.createResultDto();

        outputView.printOriginalTotalAmount(orders.calculateOriginalTotalAmount());
        outputView.printGiveAway(matchingEvents.containsGiveAway());





        Console.close();
    }

    private void initializeInputHandlers() {
        //TODO VisitingDateInputHandler, OrdersInputHandler 생성
        visitingDateInputHandler = VisitingDateInputHandler.of(inputView, outputView);
        ordersInputHandler = OrdersInputHandler.of(inputView, outputView);
    }
}
