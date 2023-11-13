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
    private static final String RESULT_SRART_FORMAT = "12월 $d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
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
    private static final String EXPECTED_TOTAL_AMOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TITLE = "<12월 이벤트 배지>";

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printStart() {
        System.out.println(START_MESSAGE);
    }

    public void printResultStart(int date) {
        String message = String.format(RESULT_SRART_FORMAT, date);
        System.out.println(message);
        printBlank();
    }

    private void printBlank() {
        System.out.println();
    }

    public void printMenu(OrdersDto ordersDto) {
        System.out.println(MENU_TITLE);
        ordersDto.getOrderItemDtos()
                .forEach(item ->
                        System.out.printf((MENU_FORMAT) + "%n", item.getMenuName(), item.getQuantity()));
        printBlank();
    }

    public void printResult(MatchingEventsDto dto) {
        printOriginalTotalAmount(dto.getOriginalTotalAmount());
        printGiveAway(dto.isContainsGiveAway());
        printMatchingEvents(dto.getEvents());
        printTotalBenefitAmount(dto.getTotalBenefitAmount());
        printExpectedTotalAmount(dto.getExpectedTotalAmount());
        printBadge(dto.getBadgeName());
    }

    private void printOriginalTotalAmount(long originalTotalAmount) {
        System.out.println(ORIGINAL_TOTAL_AMOUNT_TITLE);
        String amount = formatAmount(originalTotalAmount);
        System.out.println(String.format(TOTAL_AMOUNT_FORMAT, amount));
        printBlank();
    }

    private String formatAmount(long amount) {
        DecimalFormat df = new DecimalFormat("###,###,###,###");  //TODO "###,###,###,###" 상수 처리
        return df.format(amount);
    }

    private void printGiveAway(boolean containsGiveAway) {
        System.out.println(GIVE_AWAY_TITLE);
        if (containsGiveAway) {
            System.out.println(GIVE_AWAY_FORMAT);
            printBlank();
            return;
        }
        printDefault();
    }

    private void printDefault() {
        System.out.println(DEFAULT);
        printBlank();
    }

    private void printMatchingEvents(List<EventDetailDto> events) {
        System.out.println(MATCHING_EVENTS_TITLE);
        if (events.isEmpty()) {
            printDefault();
            return;
        }
        events.forEach(this::printEventDetail);
        printBlank();
    }

    private void printEventDetail(EventDetailDto event) {
        String formattedAmount = formatAmount(event.getBenefitAmount());
        String message = String.format(MATCHING_EVENT_FORMAT, event.getEventName(), formattedAmount);
        System.out.println(message);
    }

    private void printTotalBenefitAmount(long totalBenefitAmount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_TITLE);
        printDefaultWhenAmountIsZero(totalBenefitAmount);
        String amount = formatAmount(totalBenefitAmount);
        String message = String.format(TOTAL_BENEFIT_AMOUNT_FORMAT, amount);
        System.out.println(message);
        printBlank();
    }

    private void printExpectedTotalAmount(long discountedTotalAmount) {
        System.out.println(EXPECTED_TOTAL_AMOUNT_TITLE);
        printDefaultWhenAmountIsZero(discountedTotalAmount);
        String amount = formatAmount(discountedTotalAmount);
        String message = String.format(TOTAL_AMOUNT_FORMAT, amount);
        System.out.println(message);
        printBlank();
    }

    private void printDefaultWhenAmountIsZero(long amount) {
        if (amount == 0) {
            printDefault();
        }
    }

    private void printBadge(String badgeName) {
        System.out.println(BADGE_TITLE);
        System.out.println(badgeName);
        printBlank();
    }
}