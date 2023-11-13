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
    private static final String TOTAL_AMOUNT_FORMAT = "%s원";
    private static final String GIVE_AWAY_TITLE = "<증정 메뉴>";
    private static final String GIVE_AWAY_FORMAT = String.format("%s 1개", DiscountConstants.GIVE_AWAY_ITEM);
    private static final String DEFAULT = "없음";
    private static final String MATCHING_EVENTS_TITLE = "<혜택 내역>";
    private static final String MATCHING_EVENT_FORMAT = "%s: -%s원";
    private static final String TOTAL_BENEFIT_AMOUNT_TITLE = "<총혜택 금액>";
    private static final String TOTAL_BENEFIT_AMOUNT_FORMAT = "-%s원";
    private static final String DISCOUNTED_TOTAL_AMOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TITLE = "<12월 이벤트 배지>";

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
        System.out.println(String.format(TOTAL_AMOUNT_FORMAT, amount));
    }

    private String formatAmount(long amount) {
        DecimalFormat df = new DecimalFormat("###,###,###,###");  //TODO "###,###,###,###" 상수 처리
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
        String message = String.format(MATCHING_EVENT_FORMAT, event.getEventName(), formattedAmount);
        System.out.println(message);
    }

    public void printTotalBenefitAmount(long totalBenefitAmount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_TITLE);
        printDefaultWhenAmountIsZero(totalBenefitAmount);
        String amount = formatAmount(totalBenefitAmount);
        String message = String.format(TOTAL_BENEFIT_AMOUNT_FORMAT, amount);
        System.out.println(message);
    }

    public void printDiscountedTotalAmount(long discountedTotalAmount) {
        System.out.println(DISCOUNTED_TOTAL_AMOUNT_TITLE);
        printDefaultWhenAmountIsZero(discountedTotalAmount);
        String amount = formatAmount(discountedTotalAmount);
        String message = String.format(TOTAL_AMOUNT_FORMAT, amount);
        System.out.println(message);
    }

    private void printDefaultWhenAmountIsZero(long amount) {
        if (amount == 0) {
            printDefault();
        }
    }

    public void printBadge(String badgeName) {
        System.out.println(BADGE_TITLE);
        System.out.println(badgeName);
    }
}