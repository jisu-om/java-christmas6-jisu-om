package christmas.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static christmas.domain.MenuCategory.*;
import static christmas.exception.ErrorMessage.INVALID_ORDER;

public class Orders {
    private static final int MAXIMUM_ORDER_TOTAL_QUANTITY = 20;
    private final List<OrderItem> orderItems;

    private Orders(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static Orders from(List<OrderItem> orderItems) {
        validate(orderItems);
        return new Orders(orderItems);
    }

    private static void validate(List<OrderItem> orders) {
        validateDuplicates(orders);
        validateNotOnlyDrinks(orders);
        validateTotalQuantity(orders);
    }

    private static void validateDuplicates(List<OrderItem> orders) {
        //TODO validationUtils 로 빼도 좋겠다.
        Set<String> uniqueOrders = orders.stream()
                .map(item -> item.provideMenuItem().name())
                .collect(Collectors.toSet());
        if (orders.size() != uniqueOrders.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static void validateNotOnlyDrinks(List<OrderItem> orders) {
        //TODO validationUtils 로 빼도 좋겠다.
        orders.stream()
                .map(item -> item.provideMenuItem().getCategory())
                .filter(category -> !category.equals(DRINK))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER.getMessage()));
    }

    private static void validateTotalQuantity(List<OrderItem> orders) {
        //TODO validationUtils 로 빼도 좋겠다.
        int totalQuantity = orders.stream()
                .mapToInt(OrderItem::provideQuantity)
                .sum();

        if (totalQuantity > MAXIMUM_ORDER_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
