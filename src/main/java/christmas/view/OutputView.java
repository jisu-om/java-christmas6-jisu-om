package christmas.view;

import christmas.constants.DiscountConstants;
import christmas.dto.EventDetailDto;
import christmas.dto.MatchingEventsDto;
import christmas.dto.OrdersDto;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final OutputView instance = new OutputView();
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String RESULT_SRART_MESSAGE = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String MENU_TITLE = "<주문 메뉴>";
    private static final String MENU_FORMAT = "%s %d개";
    private static final String ORIGINAL_TOTAL_AMOUNT_TITLE = "<할인 전 총주문 금액>";
    private static final String ORIGINAL_TOTAL_AMOUNT_FORMAT = "%s원";
    private static final String GIVE_AWAY_TITLE = "<증정 메뉴>";
    private static final String GIVE_AWAY_FORMAT = String.format("%s 1개", DiscountConstants.GIVE_AWAY_ITEM);
    private static final String DEFAULT = "없음";
    private static final String MATCHING_EVENTS_TITLE = "<혜택 내역>";
    private static final String MATCHING_EVENT_FORMAT = "%s: -%s원";

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printStart() {
        System.out.println(START_MESSAGE);
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printResultStart() {
        System.out.println(RESULT_SRART_MESSAGE);
        printBlank();
    }

    public void printBlank() {
        System.out.println();
    }

    public void printMenu(OrdersDto ordersDto) {
        System.out.println(MENU_TITLE);
        ordersDto.getOrderItemDtos().forEach(item -> System.out.printf((MENU_FORMAT) + "%n", item.getMenuName(), item.getQuantity()));
    }

    public void printOriginalTotalAmount(long originalTotalAmount) {
        System.out.println(ORIGINAL_TOTAL_AMOUNT_TITLE);
        String amount = formatAmount(originalTotalAmount);
        System.out.println(String.format(ORIGINAL_TOTAL_AMOUNT_FORMAT, amount));
    }

    private String formatAmount(long amount) {
        DecimalFormat df = new DecimalFormat("###,###,###,###");
        return df.format(amount);
    }

    public void printGiveAway(boolean containsGiveAway) {
        System.out.println(GIVE_AWAY_TITLE);
        if (containsGiveAway) {
            System.out.println(GIVE_AWAY_FORMAT);
            return;
        }
        printDefault();
    }

    private static void printDefault() {
        System.out.println(DEFAULT);
    }

    public void printMatchingEvents(MatchingEventsDto matchingEvents) {
        System.out.println(MATCHING_EVENTS_TITLE);
        List<EventDetailDto> events = matchingEvents.getEvents();
        if (events.isEmpty()) {
            printDefault();
            return;
        }
        events.forEach(this::printEventDetail);
    }

    private void printEventDetail(EventDetailDto event) {
        String formattedAmount = formatAmount(event.getBenefitAmount());
        String eventMessage = String.format(MATCHING_EVENT_FORMAT, event.getEventName(), formattedAmount);
        System.out.println(eventMessage);
    }
}
