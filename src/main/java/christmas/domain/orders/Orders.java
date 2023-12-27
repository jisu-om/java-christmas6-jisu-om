package christmas.domain.orders;

import christmas.domain.menu.MenuType;

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

    public long calculateTotalPrice() {
        return orderItems.stream()
                .mapToLong(OrderItem::calculatePrice)
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
        return orderItems;
    }
}