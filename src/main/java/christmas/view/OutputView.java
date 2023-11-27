package christmas.view;

import christmas.dto.MatchingEventDto;
import christmas.dto.MatchingEventsDto;
import christmas.dto.OrderItemDto;
import christmas.dto.OrdersDto;

public class OutputView {
    private static final OutputView instance = new OutputView();
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String RESULT_START_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String ORDER_MENU_FORMAT = "%s %d개";
    private static final String ORIGINAL_PRICE_TITLE = "<할인 전 총주문 금액>";
    private static final String GIVE_AWAY_TITLE = "<증정 메뉴>";
    private static final String DEFAULT_MESSAGE = "없음";
    private static final String BENEFIT_TITLE = "<혜택 내역>";
    private static final String BENEFIT_FORMAT = "%s: -,%d원";
    private static final String BENEFIT_AMOUNT_TITLE = "<총혜택 금액>";
    private static final String FINAL_PRICE_TITLE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TITLE = "<12월 이벤트 배지>";

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printStart() {
        System.out.println(START_MESSAGE);
    }

    public void printResultStart() {
        System.out.println(RESULT_START_MESSAGE);
        printLine();
    }

    private static void printLine() {
        System.out.println();
    }

    public void printOrderDetail(OrdersDto ordersDto) {
        System.out.println(ORDER_MENU_TITLE);
        ordersDto.getOrderItemDtos().forEach(this::printOrder);
        printLine();
    }

    private void printOrder(OrderItemDto orderItemDto) {
        System.out.printf((ORDER_MENU_FORMAT) + "%n", orderItemDto.getName(), orderItemDto.getQuantity());
    }

    public void printMatchingEvents(MatchingEventsDto eventsDto) {
        System.out.println(BENEFIT_TITLE);
        if (eventsDto.isNone()) {
            System.out.println(DEFAULT_MESSAGE);
        }
        eventsDto.getEvents().forEach(this::printMatchingEvent);
        printLine();
    }

    private void printMatchingEvent(MatchingEventDto dto) {
        System.out.printf((BENEFIT_FORMAT) + "%n", dto.getEventName(), dto.getBenefitAmount());
    }
}
