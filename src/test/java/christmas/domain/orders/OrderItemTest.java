package christmas.domain.orders;

import christmas.domain.menu.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;


class OrderItemTest {
    @DisplayName("OrderItem 정상 생성 테스트")
    @Test
    void create() {
        // given, when
        OrderItem orderItem = OrderItem.of(Menu.시저샐러드.name(), 1);

        // then
        assertThat(orderItem).isNotNull();
    }

    @DisplayName("이름이 잘못된 경우 예외 발생한다.")
    @Test
    void exception_name() {
        assertThatThrownBy(() -> OrderItem.of("invalidMenu", 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] 수량이 {0} 인 경우 예외 발생한다")
    @ValueSource(ints = {-100, 0, 25})
    void exception_quantity(int element) {
        assertThatThrownBy(() -> OrderItem.of(Menu.바비큐립.name(), element))
                .isInstanceOf(IllegalArgumentException.class);
    }
}