package christmas.domain.orders;

import christmas.domain.menu.MenuType;

import java.util.List;

import static christmas.exception.ErrorMessage.INVALID_ORDER;

public class Orders {
    private static final int MAX_QUANTITY = 20;
    private final List<OrderItem> orderItems;

    private Orders(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static Orders from(List<OrderItem> orderItems) {
        validateOrderItems(orderItems);
        return new Orders(orderItems);
    }

    private static void validateOrderItems(List<OrderItem> orderItems) {
        validateDuplicate(orderItems);
        validateOnlyDrink(orderItems);
        validateSize(orderItems);
    }

    private static void validateDuplicate(List<OrderItem> orderItems) {
        long uniqueMenuCount = orderItems.stream()
                .map(OrderItem::provideMenu)
                .distinct()
                .count();
        if (orderItems.size() != uniqueMenuCount) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static void validateOnlyDrink(List<OrderItem> orderItems) {
        boolean isOnlyDrink = orderItems.stream()
                .allMatch(item -> item.isMenuType(MenuType.DRINK));
        if (isOnlyDrink) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static void validateSize(List<OrderItem> orderItems) {
        int totalQuantity = orderItems.stream()
                .mapToInt(OrderItem::provideQuantity)
                .sum();
        if (totalQuantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public boolean isTotalPriceMoreThan(int totalPrice) {
        return calculateTotalPrice() >= totalPrice;
    }

    public int calculateTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::calculatePrice)
                .sum();
    }

    public boolean containsMenuType(MenuType type) {
        return orderItems.stream()
                .anyMatch(item -> item.isMenuType(type));
    }

    public int countMenuType(MenuType type) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.isMenuType(type))
                .mapToInt(OrderItem::provideQuantity)
                .sum();
    }

    public List<OrderItem> provideOrderItems() {
        return List.copyOf(orderItems);
    }
}