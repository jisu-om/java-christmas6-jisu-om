package christmas.validationUtils;

import static christmas.exception.ErrorMessage.INVALID_ORDERS;

public class OrderItemValidationUtils {
    private static final int MINIMUM_QUANTITY = 1;

    public static void validatePositive(int quantity) {
        if (quantity < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }
}
