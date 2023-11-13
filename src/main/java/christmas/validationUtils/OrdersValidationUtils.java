package christmas.validationUtils;

import christmas.domain.menu.MenuItem;
import christmas.domain.orders.OrderItem;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static christmas.domain.menu.MenuCategory.DRINK;
import static christmas.exception.ErrorMessage.INVALID_ORDERS;

public class OrdersValidationUtils {
    private static final int MAXIMUM_ORDER_TOTAL_QUANTITY = 20;

    public static void validateDuplicates(List<OrderItem> orders) {
        Set<MenuItem> uniqueOrders = orders.stream()
                .map(OrderItem::provideMenuItem)
                .collect(Collectors.toSet());
        if (orders.size() != uniqueOrders.size()) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    public static void validateNotOnlyDrinks(List<OrderItem> orders) {
        orders.stream()
                .map(item -> item.provideMenuItem().getCategory())
                .filter(category -> !category.equals(DRINK))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDERS.getMessage()));
    }

    public static void validateTotalQuantity(List<OrderItem> orders) {
        int totalQuantity = orders.stream()
                .mapToInt(OrderItem::provideQuantity)
                .sum();

        if (totalQuantity > MAXIMUM_ORDER_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }
}
