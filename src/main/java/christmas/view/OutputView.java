package christmas.view;

import christmas.domain.event.MatchingEvent;
import christmas.dto.ResultDto;
import christmas.dto.OrdersDto;

import java.util.List;

import static christmas.view.OutputViewMessageConstants.*;

public class OutputView {
    private static final OutputView instance = new OutputView();

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
        System.out.printf((RESULT_SRART_FORMAT) + "%n", date);
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

    public void printResult(ResultDto dto) {
        printOriginalTotalAmount(dto.getOriginalTotalAmount());
        printGiveAway(dto.isContainsGiveAway());
        printMatchingEvents(dto.getEventDetails());
        printTotalBenefitAmount(dto.getTotalBenefitAmount());
        printExpectedTotalAmount(dto.getExpectedTotalAmount());
        printBadge(dto.getBadgeName());
    }

    private void printOriginalTotalAmount(long amount) {
        System.out.println(ORIGINAL_TOTAL_AMOUNT_TITLE);
        System.out.printf((TOTAL_AMOUNT_FORMAT) + "%n", amount);
        printBlank();
    }

    private void printGiveAway(boolean containsGiveAway) {
        System.out.println(GIVE_AWAY_TITLE);
        if (containsGiveAway) {
            printMessage(GIVE_AWAY_FORMAT);
            return;
        }
        printMessage(DEFAULT);
    }

    private void printMessage(String message) {
        System.out.println(message);
        printBlank();
    }

    private void printMatchingEvents(List<MatchingEvent> events) {
        System.out.println(MATCHING_EVENTS_TITLE);
        if (events.isEmpty()) {
            printMessage(DEFAULT);
            return;
        }
        events.forEach(this::printEventDetail);
        printBlank();
    }

    private void printEventDetail(MatchingEvent event) {
        System.out.printf((MATCHING_EVENT_FORMAT) + "%n", event.getEventName(), event.getBenefitAmount());
    }

    private void printTotalBenefitAmount(long amount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_TITLE);
        if (amount == 0) {
            printMessage(TOTAL_BENEFIT_AMOUNT_DEFAULT_MESSAGE);
            return;
        }
        System.out.printf((TOTAL_BENEFIT_AMOUNT_FORMAT) + "%n", amount);
        printBlank();
    }

    private void printExpectedTotalAmount(long amount) {
        System.out.println(EXPECTED_TOTAL_AMOUNT_TITLE);
        System.out.printf((TOTAL_AMOUNT_FORMAT) + "%n", amount);
        printBlank();
    }

    private void printBadge(String badgeName) {
        System.out.println(BADGE_TITLE);
        System.out.println(badgeName);
        printBlank();
    }
}