package christmas.dto;

import christmas.domain.orders.Orders;

import java.util.List;

public class OrdersDto {
    private final List<OrderItemDto> orderItemDtos;

    private OrdersDto(List<OrderItemDto> orderItemDtos) {
        this.orderItemDtos = orderItemDtos;
    }

    public static OrdersDto from(Orders orders) {
        List<OrderItemDto> orderItemDtos = orders.getOrderItems().stream()
                .map(OrderItemDto::of)
                .toList();
        return new OrdersDto(orderItemDtos);
    }

    public List<OrderItemDto> getOrderItemDtos() {
        return List.copyOf(orderItemDtos);
    }
}
