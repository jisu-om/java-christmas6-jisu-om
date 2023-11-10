package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrdersTest {
    private static final String VALID_EPITIZER = "양송이수프";
    private static final String VALID_MAIN = "티본스테이크";
    private static final String VALID_DRINK1 = "제로콜라";
    private static final String VALID_DRINK2 = "샴페인";
    private static final String INVALID_MENU = "abc";
    private static final int VALID_QUANTITY1 = 1;
    private static final int VALID_QUANTITY2 = 2;
    private static final int VALID_QUANTITY10 = 10;
    private static final int ZERO_QUANTITY = 0;
    private static final int NEGATIVE_QUANTITY = -10;

    Map<String, Integer> orderItems;
    Orders orders;

    @DisplayName("Menu에 있는 것을 전달받는 경우 Orders 정상 생성")
    @Test
    void create() {
        //given
        orderItems = Map.of(VALID_EPITIZER, VALID_QUANTITY1);

        //when
        orders = Orders.from(orderItems);

        //then
        assertThat(orders).isNotNull();
    }

    @DisplayName("메뉴가 존재하지 않는 경우 예외가 발생한다.")
    @Test
    void exception_no_menu() {
        // given
        orderItems = Map.of(INVALID_MENU, VALID_QUANTITY1);

        // when, then
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 중복된 경우 예외가 발생한다.")
    @Test
    void exception_duplicated_menus() {
        // given
        orderItems = Map.of(
                VALID_EPITIZER, VALID_QUANTITY1,
                VALID_EPITIZER, VALID_QUANTITY2);

        // when, then
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    @Test
    void exception_orders_drink_only1() {
        // given
        orderItems = Map.of(VALID_DRINK1, VALID_QUANTITY2);

        // when, then
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    @Test
    void exception_orders_drink_only2() {
        // given
        orderItems = Map.of(
                VALID_DRINK1, VALID_QUANTITY2,
                VALID_DRINK2, VALID_QUANTITY2);

        // when, then
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 개수가 0이면 예외가 발생한다.")
    @Test
    void exception_invalid_menu_quantity_zero() {
        // given
        orderItems = Map.of(VALID_EPITIZER, ZERO_QUANTITY);

        // when, then
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 개수가 음수면 예외가 발생한다.")
    @Test
    void exception_invalid_menu_quantity_negative() {
        // given
        orderItems = Map.of(VALID_EPITIZER, NEGATIVE_QUANTITY);

        // when, then
        assertThatThrownBy(() -> Orders.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 개수의 총합이 20개 초과이면 예외가 발생한다.")
    @Test
    void exception_invalid_menu_total_quantity() {
        // given
        orderItems = Map.of(
                VALID_EPITIZER, VALID_QUANTITY10,
                VALID_MAIN, VALID_QUANTITY10,
                VALID_DRINK1, VALID_QUANTITY1,
                VALID_DRINK2, VALID_QUANTITY2);

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
