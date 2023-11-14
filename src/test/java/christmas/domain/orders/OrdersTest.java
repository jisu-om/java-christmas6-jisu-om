package christmas.domain.orders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrdersTest {
    String APPETIZER = "양송이수프";
    String MAIN = "티본스테이크";
    String DRINK1 = "제로콜라";
    String DRINK2 = "샴페인";
    List<OrderItem> orderItems;

    private static Stream<Arguments> orderItemsProvider() {
        OrdersTest test = new OrdersTest();

        OrderItem appetizer1 = OrderItem.of(test.APPETIZER, 1);
        OrderItem appetizer2 = OrderItem.of(test.APPETIZER, 2);
        OrderItem appetizer10 = OrderItem.of(test.APPETIZER, 10);
        OrderItem drink1 = OrderItem.of(test.DRINK1, 1);
        OrderItem drink2 = OrderItem.of(test.DRINK2, 1);
        OrderItem main10 = OrderItem.of(test.MAIN, 10);

        return Stream.of(
                Arguments.of(List.of(appetizer1, appetizer2)),
                Arguments.of(List.of(drink1)),
                Arguments.of(List.of(drink1, drink2)),
                Arguments.of(List.of(appetizer10, main10, drink1))
        );
    }

    @DisplayName("Orders 정상 생성")
    @Test
    void create() {
        //given
        OrderItem orderItem1 = OrderItem.of(APPETIZER, 1);
        OrderItem orderItem2 = OrderItem.of(DRINK1, 2);
        orderItems = List.of(orderItem1, orderItem2);

        //when
        Orders orders = Orders.from(orderItems);

        //then
        assertThat(orders).isNotNull();
    }

    @ParameterizedTest(name = "[{index}] 중복 메뉴, 음료만 주문, 메뉴 개수 총합이 20 초과인 경우, 예외가 발생한다.")
    @MethodSource("orderItemsProvider")
    void exception(List<OrderItem> orderItems) {
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
