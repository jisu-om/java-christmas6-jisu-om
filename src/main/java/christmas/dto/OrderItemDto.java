package christmas.dto;

import christmas.domain.orders.OrderItem;

public class OrderItemDto {
    private final String menuName;
    private final int quantity;

    private OrderItemDto(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public static OrderItemDto of(String menuName, int quantity) {
        return new OrderItemDto(menuName, quantity);
    }

    public OrderItem toOrderItem() {
        return OrderItem.of(menuName, quantity);
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}
