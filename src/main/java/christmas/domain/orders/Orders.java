package christmas.domain.orders;

import christmas.utils.OrdersValidationUtils;

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

    public int countOrderItemByCategory(MenuCategory comparingCategory) {
        return orderItems.stream()
                .filter(item -> item.convertToCategory().equals(comparingCategory))
                .mapToInt(OrderItem::provideQuantity)
                .sum();
    }

    public boolean existsOrderItemByCategory(MenuCategory comparingCategory) {
        return orderItems.stream()
                .map(OrderItem::convertToCategory)
                .anyMatch(category -> category.equals(comparingCategory));
    }

    public long calculateOriginalTotalAmount() {
        return orderItems.stream()
                .mapToLong(OrderItem::calculateItemOriginalPrice)
                .sum();
    }

    public List<OrderItem> provideOrderItems() {
        return List.copyOf(orderItems);
    }
}