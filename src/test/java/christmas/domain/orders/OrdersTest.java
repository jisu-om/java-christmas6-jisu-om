package christmas.domain.orders;


import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class OrdersTest {
    @DisplayName("Orders 정상 생성 테스트")
    @Test
    void create() {
        // given
        OrderItem item1 = OrderItem.of(Menu.시저샐러드.name(), 1);
        OrderItem item2 = OrderItem.of(Menu.바비큐립.name(), 1);
        OrderItem item3 = OrderItem.of(Menu.레드와인.name(), 2);

        //when
        Orders orders = Orders.from(List.of(item1, item2, item3));

        //then
        assertThat(orders).isNotNull();
    }

    @ParameterizedTest(name = "[{index}] 예외가 발생한다.")
    @MethodSource("orderItemsProvider")
    void exception(List<OrderItem> orderItems) {
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> orderItemsProvider() {
        OrderItem item1 = OrderItem.of(Menu.레드와인.name(), 2);
        OrderItem item2 = OrderItem.of(Menu.샴페인.name(), 2);
        OrderItem item3 = OrderItem.of(Menu.바비큐립.name(), 10);
        OrderItem item4 = OrderItem.of(Menu.샴페인.name(), 12);

        return Stream.of(
                Arguments.of(List.of(item1, item2)),
                Arguments.of(List.of(item3, item4)),
                Arguments.of(List.of(item3, item3))
        );
    }

}