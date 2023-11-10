package christmas.domain;

import christmas.validationUtils.OrdersValidationUtils;

import java.util.List;

public class Orders {
    private final List<OrderItem> orderItems;

    private Orders(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static Orders from(List<OrderItem> orderItems) {
        validate(orderItems);
        return new Orders(orderItems);
    }

    private static void validate(List<OrderItem> orders) {
        OrdersValidationUtils.validateDuplicates(orders);
        OrdersValidationUtils.validateNotOnlyDrinks(orders);
        OrdersValidationUtils.validateTotalQuantity(orders);
    }
}
