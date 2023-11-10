package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderTest {
    String VALID_MENU = "양송이수프";
    String INVALID_MENU = "abc";
    int VALID_QUANTITY = 1;
    int INVALID_QUANTITY_ZERO = 0;
    int INVALID_QUANTITY_NEGATIVE = -10;

    @DisplayName("Order 정상 생성")
    @Test
    void create() {
        // given, when
        Order order = Order.of(VALID_MENU, VALID_QUANTITY);

        // then
        assertThat(order).isNotNull();
    }

    @DisplayName("존재하지 않는 메뉴면 Order 생성 시 예외가 발생한다.")
    @Test
    void exception_invalid_menu() {
        // when, then
        assertThatThrownBy(() -> Order.of(INVALID_MENU, VALID_QUANTITY))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("매뉴 개수가 0이면 Order 생성 시 예외가 발생한다.")
    @Test
    void exception_invalid_menu_quantity_zero() {
        // when, then
        assertThatThrownBy(() -> Order.of(VALID_MENU, INVALID_QUANTITY_ZERO))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 개수가 음수면 Order 생성 시 예외가 발생한다.")
    @Test
    void exception_invalid_menu_quantity_negative() {
        // when, then
        assertThatThrownBy(() -> Order.of(VALID_MENU, INVALID_QUANTITY_NEGATIVE))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
