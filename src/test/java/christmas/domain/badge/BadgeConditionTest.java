package christmas.domain.badge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeConditionTest {
    @ParameterizedTest(name = "[{index}] 총혜택금액이 {0} 이면 이벤트 배지는 {1} 이다.")
    @CsvSource(value =
            {"0, '없음'", "1000, '없음'", "5000, '별'", "9900, '별'",
                    "10000, '트리'", "19000, '트리'",
                    "20000, '산타'", "1000000, '산타'"})
    void findBadgeNameByCondition(long amount, String badgeName) {
        BadgeCondition badge = BadgeCondition.findBadgeByCondition(amount);
        assertThat(badge.getBadgeName()).isEqualTo(badgeName);
    }
}