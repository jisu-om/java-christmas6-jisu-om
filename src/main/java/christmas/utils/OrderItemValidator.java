package christmas.utils;

import static christmas.exception.ErrorMessage.INVALID_ORDERS;

public class OrderItemValidator {
    public static void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }
}
