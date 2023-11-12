package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.ChristmasManager;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.dto.EventsDto;
import christmas.dto.OrdersDto;
import christmas.dto.PriceDetailDto;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private VisitingDateInputHandler visitingDateInputHandler;
    private  OrdersInputHandler ordersInputHandler;

    private ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static ChristmasController of(InputView inputView, OutputView outputView) {
        return new ChristmasController(inputView, outputView);
    }

    public void run() {
        initializeInputHandlers();

        //TODO readVisitingDate() : visitingDateInputHandler 를 통해 VisitingDate 생성 (예외 발생 시 다시 받기)
        //TODO readOrders() : ordersInputHandler 를 통해 Orders 생성 (예외 발생 시 다시 받기)
        VisitingDate date = visitingDateInputHandler.createVisitingDate();
        Orders orders = ordersInputHandler.createOrders();

        //TODO Orders 를 바탕으로 OrdersDto 생성해서 outputView 에 전달 (주문 메뉴 출력)
        OrdersDto ordersDto = OrdersDto.of(orders);

        //TODO visitingDate, orders 로 ChristmasManager 생성
        ChristmasManager christmasManager = ChristmasManager.of(date, orders);

        //TODO christmasManager 에게 할인전 총주문금액, 혜택내역, 할인금액, 총혜택금액, 할인후 예상결제금액 계산 후
        // EventsDto, PriceDetailDto 생성하라고 요청
        // (따로따로 객체 생성? ex. PricingInfo - 이들이 객체로서 하는 로직이 있는 경우에 생성)
        EventsDto eventsDto = christmasManager.createEventsDto();
        PriceDetailDto priceDetailDto = christmasManager.createPriceDetailDto();

        //TODO eventsDto, priceDetailDto 를 outputView 에 넘겨줌


        Console.close();
    }

    private void initializeInputHandlers() {
        //TODO VisitingDateInputHandler, OrdersInputHandler 생성
    }
}
