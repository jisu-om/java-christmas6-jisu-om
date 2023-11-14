package christmas.domain.orders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderItemTest {
    @DisplayName("OrderItem 정상 생성")
    @Test
    void create() {
        // given, when
        OrderItem orderItem = OrderItem.of("양송이수프", 1);

        // then
        assertThat(orderItem).isNotNull();
    }

    @ParameterizedTest(name = "[{index}] 메뉴이름:{0}, 수량:{1} 인 경우, OrderItem 생성 시 예외가 발생한다.")
    @CsvSource(value = {"abc, 1", "양송이수프, 0", "양송이수프, -1"})
    void exception(String menu, int quantity) {
        assertThatThrownBy(() -> OrderItem.of(menu, quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
