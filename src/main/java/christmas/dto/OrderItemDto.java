package christmas.dto;

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

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
