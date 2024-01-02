package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderItemDto;
import christmas.view.validator.OrdersValidator;
import christmas.view.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

import static christmas.exception.ErrorMessage.*;

public class InputView {
    private static final InputView instance = new InputView();
    private static final String VISITING_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDERS_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String ORDERS_DELIMITER = ",";
    private static final String QUANTITY_DELIMITER = "-";
    private static final int ORDER_MENU_INDEX = 0;
    private static final int ORDER_QUANTITY_INDEX = 1;


    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    public int readVisitingDate() {
        System.out.println(VISITING_DATE_MESSAGE);
        String input = Console.readLine();
        return Validator.safeParseInt(input, INVALID_VISITING_DATE.getMessage());
    }

    public List<OrderItemDto> readOrderItemDtos() {
        System.out.println(ORDERS_MESSAGE);
        String input = Console.readLine();
        List<String> orders = OrdersValidator.safeSplit(input, ORDERS_DELIMITER);
        return convertToOrderItemDtos(orders);
    }

    private List<OrderItemDto> convertToOrderItemDtos(List<String> orders) {
        return orders.stream()
                .map(order -> OrdersValidator.safeSplit(order, QUANTITY_DELIMITER))
                .map(this::pairToOrderItemDto)
                .collect(Collectors.toList());
    }

    private OrderItemDto pairToOrderItemDto(List<String> pair) {
        OrdersValidator.validatePair(pair);
        String menuName = pair.get(ORDER_MENU_INDEX);
        int quantity = Validator.safeParseInt(pair.get(ORDER_QUANTITY_INDEX), INVALID_ORDER.getMessage());
        return OrderItemDto.of(menuName, quantity);
    }
}