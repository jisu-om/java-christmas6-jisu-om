package christmas.utils;

import static christmas.exception.ErrorMessage.INVALID_ORDER;
import static christmas.utils.OrdersValidator.MAXIMUM_TOTAL_QUANTITY;

public class OrderItemValidator {
    public static void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public static void validateSize(int value) {
        if (value > MAXIMUM_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
