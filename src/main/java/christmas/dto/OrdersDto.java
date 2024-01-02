package christmas.dto;

import christmas.domain.orders.Orders;

import java.util.List;

public record OrdersDto(List<OrderItemDto> orderItemDtos) {
    public static OrdersDto from(Orders orders) {
        return new OrdersDto(orders.provideOrderItems().stream()
                .map(OrderItemDto::from)
                .toList());
    }
}
