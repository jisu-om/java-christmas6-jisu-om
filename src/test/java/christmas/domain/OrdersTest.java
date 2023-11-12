package christmas.domain;

import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrdersTest {
    String VALID_MENU = "양송이수프";
    String VALID_MAIN = "티본스테이크";
    String VALID_DRINK1 = "제로콜라";
    String VALID_DRINK2 = "샴페인";
    int VALID_QUANTITY1 = 1;
    int VALID_QUANTITY2 = 2;
    int VALID_QUANTITY10 = 10;
    List<OrderItem> orderItems;
    Orders orders;

    @DisplayName("Orders 정상 생성")
    @Test
    void create() {
        //given
        OrderItem orderItem1 = OrderItem.of(VALID_MENU, VALID_QUANTITY1);
        OrderItem orderItem2 = OrderItem.of(VALID_DRINK1, VALID_QUANTITY1);
        orderItems = List.of(orderItem1, orderItem2);

        //when
        orders = Orders.from(orderItems);

        //then
        assertThat(orders).isNotNull();
    }

    @DisplayName("메뉴가 중복된 경우 예외가 발생한다.")
    @Test
    void exception_duplicated_menus() {
        // given
        OrderItem orderItem1 = OrderItem.of(VALID_MENU, VALID_QUANTITY1);
        OrderItem orderItem2 = OrderItem.of(VALID_MENU, VALID_QUANTITY2);
        orderItems = List.of(orderItem1, orderItem2);

        // when, then
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    @Test
    void exception_orders_drink_only() {
        // given
        OrderItem orderItem1 = OrderItem.of(VALID_DRINK1, VALID_QUANTITY1);
        OrderItem orderItem2 = OrderItem.of(VALID_DRINK2, VALID_QUANTITY1);
        orderItems = List.of(orderItem1, orderItem2);

        // when, then
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 개수의 총합이 20개 초과이면 예외가 발생한다.")
    @Test
    void exception_invalid_menu_total_quantity() {
        // given
        OrderItem orderItem1 = OrderItem.of(VALID_MENU, VALID_QUANTITY10);
        OrderItem orderItem2 = OrderItem.of(VALID_MAIN, VALID_QUANTITY10);
        OrderItem orderItem3 = OrderItem.of(VALID_DRINK1, VALID_QUANTITY1);
        orderItems = List.of(orderItem1, orderItem2, orderItem3);

        // when, then
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

//    @ParameterizedTest(name = "[{index}] {0} ")
//    @MethodSource("provideTestCases")
//    void exception(Map<String, Integer> testCase) {
//        // when, then
//        assertThatThrownBy(() -> Orders.of(testCase))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//
//    static Stream<Map.Entry<String, Integer>> provideTestCases() {
//        return Stream.of(
//                new AbstractMap.SimpleEntry<>("test1", 1),
//                new AbstractMap.SimpleEntry<>("test2", 2),
//                new AbstractMap.SimpleEntry<>("test3", 3)
//        );
//    }
}
