package christmas.dto;

import christmas.domain.orders.OrderItem;

public class OrderItemDto {
    private final String name;
    private final int quantity;

    private OrderItemDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static OrderItemDto of(String name, int quantity) {
        return new OrderItemDto(name, quantity);
    }

    public static OrderItemDto from(OrderItem orderItem) {
        return new OrderItemDto(orderItem.provideMenuName(), orderItem.provideQuantity());
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
