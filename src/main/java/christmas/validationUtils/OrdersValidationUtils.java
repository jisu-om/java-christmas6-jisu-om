package christmas.validationUtils;

import christmas.domain.OrderItem;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static christmas.domain.MenuCategory.DRINK;
import static christmas.exception.ErrorMessage.INVALID_ORDER;

public class OrdersValidationUtils {
    private static final int MAXIMUM_ORDER_TOTAL_QUANTITY = 20;

    public static void validateDuplicates(List<OrderItem> orders) {
        Set<String> uniqueOrders = orders.stream()
                .map(item -> item.provideMenuItem().name())
                .collect(Collectors.toSet());
        if (orders.size() != uniqueOrders.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public static void validateNotOnlyDrinks(List<OrderItem> orders) {
        orders.stream()
                .map(item -> item.provideMenuItem().getCategory())
                .filter(category -> !category.equals(DRINK))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER.getMessage()));
    }

    public static void validateTotalQuantity(List<OrderItem> orders) {
        int totalQuantity = orders.stream()
                .mapToInt(OrderItem::provideQuantity)
                .sum();

        if (totalQuantity > MAXIMUM_ORDER_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
