package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuItemTest {
    @DisplayName("findByName 테스트")
    @Test
    void findByName() {
        MenuItem item = MenuItem.findByName("타파스");
        assertThat(item.getCategory()).isEqualTo(MenuCategory.APPETIZER);
    }
}