package christmas.domain.orders;

import static christmas.exception.ErrorMessage.INVALID_ORDER;

public class Quantity {
    private static final int MIN_QUANTITY = 1;
    private final int quantity;

    private Quantity(int quantity) {
        this.quantity = quantity;
    }

    public static Quantity from(int quantity) {
        validateQuantity(quantity);
        return new Quantity(quantity);
    }
    private static void validateQuantity(int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public int getQuantity() {
        return quantity;
    }
}
