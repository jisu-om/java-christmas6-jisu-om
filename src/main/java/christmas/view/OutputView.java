package christmas.view;

import christmas.dto.*;
import org.junit.platform.commons.util.StringUtils;

public class OutputView {
    private static final OutputView instance = new OutputView();
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String RESULT_START_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String ORDER_MENU_FORMAT = "%s %d개";
    private static final String ORIGINAL_AMOUNT_TITLE = "<할인 전 총주문 금액>";
    private static final String ORIGINAL_AMOUNT_FORMAT = "%,d원";
    private static final String GIVE_AWAY_TITLE = "<증정 메뉴>";
    private static final String GIVE_AWAY_FORMAT = "%s %d개";
    private static final String DEFAULT_MESSAGE = "없음";
    private static final String BENEFIT_TITLE = "<혜택 내역>";
    private static final String BENEFIT_AMOUNT_TITLE = "<총혜택 금액>";
    private static final String BENEFIT_AMOUNT_FORMAT = "%,d원";
    private static final int BENEFIT_AMOUNT_UNIT = -1;
    private static final String FINAL_AMOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String FINAL_AMOUNT_FORMAT = "%,d원";
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
    }

    public void printOrderDetail(OrdersDto ordersDto) {
        printLine();
        System.out.println(ORDER_MENU_TITLE);
        ordersDto.orderItemDtos().forEach(this::printOrder);
    }

    private static void printLine() {
        System.out.println();
    }

    private void printOrder(OrderItemDto orderItemDto) {
        System.out.printf((ORDER_MENU_FORMAT) + "%n", orderItemDto.getName(), orderItemDto.getQuantity());
    }

    public void printResult(ResultDto resultDto) {
        printOriginalAmount(resultDto.originalPrice());
        printGiveaway(resultDto.containsGiveaway(), resultDto.giveawayItemName());
        printMatchingEvents(resultDto.eventsDto());
        printBenefitAmount(resultDto.totalBenefitAmount());
        printFinalAmount(resultDto.finalPrice());
        printBadgeName(resultDto.badgeDto().name());
    }

    private void printOriginalAmount(long amount) {
        printLine();
        System.out.println(ORIGINAL_AMOUNT_TITLE);
        System.out.printf((ORIGINAL_AMOUNT_FORMAT) + "%n", amount);
    }

    private void printGiveaway(boolean containsGiveaway, String giveawayItemName) {
        printLine();
        System.out.println(GIVE_AWAY_TITLE);
        if (containsGiveaway) {
            System.out.printf((GIVE_AWAY_FORMAT) + "%n", giveawayItemName, 1);
            return;
        }
        System.out.println(DEFAULT_MESSAGE);
    }

    private void printMatchingEvents(EventsDto eventsDto) {
        printLine();
        System.out.println(BENEFIT_TITLE);
        if (eventsDto.eventDtos().isEmpty()) {
            System.out.println(DEFAULT_MESSAGE);
        }
        eventsDto.eventDtos().forEach(this::printMatchingEvent);
    }

    private void printMatchingEvent(EventDto eventDto) {
        if (StringUtils.isBlank(eventDto.name())) {
            System.out.println(DEFAULT_MESSAGE);
        }
    }

    public void printBenefitAmount(long benefitAmount) {
        printLine();
        System.out.println(BENEFIT_AMOUNT_TITLE);
        System.out.printf((BENEFIT_AMOUNT_FORMAT) + "%n", benefitAmount * BENEFIT_AMOUNT_UNIT);
    }

    private void printFinalAmount(long finalAmount) {
        printLine();
        System.out.println(FINAL_AMOUNT_TITLE);
        System.out.printf((FINAL_AMOUNT_FORMAT) + "%n", finalAmount);
    }

    private void printBadgeName(String badgeName) {
        printLine();
        System.out.println(BADGE_TITLE);
        if (badgeName == null) {
            System.out.println(DEFAULT_MESSAGE);
            return;
        }
        System.out.println(badgeName);
    }
}