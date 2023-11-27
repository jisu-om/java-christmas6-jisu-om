package christmas.utils;

import christmas.domain.orders.OrderItem;
import org.junit.platform.commons.util.StringUtils;

import java.util.List;

import static christmas.domain.menu.MenuType.DRINK;
import static christmas.exception.ErrorMessage.INVALID_ORDERS;

public class OrdersValidator {
    private static final int ORDER_PAIR_SIZE = 2;
    private static final int MAXIMUM_TOTAL_QUANTITY = 20;

    public static List<String> safeSplit(String input, String delimiter) {
        validateEmpty(input);
        validateStartsOrEndsWithDelimiter(input, delimiter);
        return List.of(input.split(delimiter));
    }

    private static void validateEmpty(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    private static void validateStartsOrEndsWithDelimiter(String input, String delimiter) {
        if (input.startsWith(delimiter) || input.endsWith(delimiter)) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    public static int safeParseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    public static void validatePair(List<String> pair) {
        if (pair.size() != ORDER_PAIR_SIZE) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    public static void validateDuplicate(List<OrderItem> orderItems) {
        long uniqueMenuCount = orderItems.stream()
                .map(OrderItem::provideMenu)
                .distinct()
                .count();
        if (orderItems.size() != uniqueMenuCount) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    public static void validateOnlyDrink(List<OrderItem> orderItems) {
        boolean isOnlyDrink = orderItems.stream()
                .allMatch(item -> item.isMenuType(DRINK));
        if (isOnlyDrink) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    public static void validateSize(List<OrderItem> orderItems) {
        int totalQuantity = orderItems.stream()
                .mapToInt(OrderItem::provideQuantity)
                .sum();
        if (totalQuantity > MAXIMUM_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }
}
