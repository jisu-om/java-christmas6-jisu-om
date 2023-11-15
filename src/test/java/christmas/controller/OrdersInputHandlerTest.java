package christmas.controller;


import christmas.domain.orders.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;

class OrdersInputHandlerTest {
    @DisplayName("createOrders 정상 테스트")
    @Test
    void createOrders_o() {
        // given
        OrdersInputHandler ordersInputHandler = OrdersInputHandler.of(InputView.getInstance(), OutputView.getInstance());
        command("티본스테이크-1,레드와인-2");

        // when
        Orders orders = ordersInputHandler.createOrders();

        // then
        assertThat(orders).isNotNull();
    }

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }
}