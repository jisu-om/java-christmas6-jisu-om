package christmas.domain;

import christmas.utils.OrdersValidator;

import java.util.List;

public class Orders {
    private final List<OrderItem> orderItems;

    private Orders(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static Orders from(List<OrderItem> orderItems) {
        OrdersValidator.validateDuplicate(orderItems);
        OrdersValidator.validateOnlyDrink(orderItems);
        OrdersValidator.validateSize(orderItems);
        return new Orders(orderItems);
    }
}
