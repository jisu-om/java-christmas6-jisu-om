package christmas.domain.badge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BadgeGeneratorTest {
    @DisplayName("혜택금액에 따른 이벤트 배지 생성")
    @Test
    void create_badge() {
        //when, then
        assertThat(BadgeGenerator.findBadgeName(0)).isEqualTo(BadgeCondition.NONE.getBadgeName());
        assertThat(BadgeGenerator.findBadgeName(5000)).isEqualTo(BadgeCondition.STAR.getBadgeName());
        assertThat(BadgeGenerator.findBadgeName(10000)).isEqualTo(BadgeCondition.TREE.getBadgeName());
        assertThat(BadgeGenerator.findBadgeName(20000)).isEqualTo(BadgeCondition.SANTA.getBadgeName());
    }
}
