package christmas.dto;

import christmas.domain.orders.OrderItem;

public record OrderItemDto(String name, int quantity) {
    public static OrderItemDto of(String name, int quantity) {
        return new OrderItemDto(name, quantity);
    }

    public static OrderItemDto from(OrderItem orderItem) {
        return new OrderItemDto(orderItem.provideMenuName(), orderItem.provideQuantity());
    }
}
