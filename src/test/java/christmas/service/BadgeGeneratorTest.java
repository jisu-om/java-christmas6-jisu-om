package christmas.service;

import christmas.domain.badge.BadgeCondition;
import christmas.domain.badge.BadgeGenerator;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
