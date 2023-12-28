package christmas.dto;

import christmas.domain.orders.Orders;

import java.util.List;

public record OrdersDto(List<OrderItemDto> orderItemDtos) {
    public static OrdersDto from(Orders orders) {
        List<OrderItemDto> orderItemDtos = orders.provideOrderItems().stream()
                .map(OrderItemDto::from)
                .toList();
        return new OrdersDto(orderItemDtos);
    }
}
