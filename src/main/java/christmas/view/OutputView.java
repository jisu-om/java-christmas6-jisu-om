package christmas.view;

import christmas.dto.OrdersDto;

import java.text.DecimalFormat;

public class OutputView {
    private static final OutputView instance = new OutputView();
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String RESULT_SRART_MESSAGE = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String MENU_START_MESSAGE = "<주문 메뉴>";
    private static final String MENU_FORMAT = "%s %d개";
    private static final String ORIGINAL_TOTAL_AMOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String ORIGINAL_TOTAL_AMOUNT_FORMAT = "%s원";

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
        System.out.println(MENU_START_MESSAGE);
        ordersDto.getOrderItemDtos()
                .forEach(item -> System.out.printf((MENU_FORMAT) + "%n", item.getMenuName(), item.getQuantity()));
    }

    public void printOriginalTotalAmount(long originalTotalAmount) {
        System.out.println(ORIGINAL_TOTAL_AMOUNT_MESSAGE);
        printFormattedAmount(originalTotalAmount);
    }

    private static void printFormattedAmount(long amount) {
        DecimalFormat df = new DecimalFormat("###,###,###,###");
        System.out.printf((ORIGINAL_TOTAL_AMOUNT_FORMAT) + "%n", df.format(amount));
    }
}
