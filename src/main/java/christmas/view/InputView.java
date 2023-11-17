package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderItemDto;
import christmas.utils.Parser;

import java.util.List;

import static christmas.exception.ErrorMessage.INVALID_DATE;
import static christmas.exception.ErrorMessage.INVALID_ORDERS;

public class InputView {
    private static final InputView instance = new InputView();
    private static final String ASK_VISITING_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDERS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String ITEM_DELIMITER = ",";
    private static final String ITEM_QUANTITY_DELIMITER = "-";
    private static final int REQUIRED_ORDER_ITEM_COMPONENTS = 2;
    private static final int MENU_NAME_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    public int readVisitingDate() {
        System.out.println(ASK_VISITING_DATE);
        String input = Console.readLine();
        return Parser.safeParseInt(input, INVALID_DATE.getMessage());
    }

    public List<OrderItemDto> readOrders() {
        System.out.println(ASK_ORDERS);
        String input = Console.readLine();
        List<String> pairs = Parser.safeSplit(input, ITEM_DELIMITER);
        return convertToOrderItemDtos(pairs);
    }

    private List<OrderItemDto> convertToOrderItemDtos(List<String> pairs) {
        return pairs.stream()
                .map(this::createOrderItemDto)
                .toList();
    }

    private OrderItemDto createOrderItemDto(String pair) {
        List<String> pairs = Parser.safeSplit(pair, ITEM_QUANTITY_DELIMITER);

        if (pairs.size() != REQUIRED_ORDER_ITEM_COMPONENTS) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
        Integer quantity = Parser.safeParseInt(pairs.get(QUANTITY_INDEX), INVALID_ORDERS.getMessage());

        return OrderItemDto.of(pairs.get(MENU_NAME_INDEX), quantity);
    }
}