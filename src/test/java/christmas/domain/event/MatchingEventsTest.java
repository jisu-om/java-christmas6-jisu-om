package christmas.domain.event;


import christmas.domain.badge.BadgeCondition;
import christmas.domain.menu.MenuItem;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MatchingEventsTest {
    @DisplayName("10000원 이하로 주문하는 경우 해당되는 이벤트 없음")
    @Test
    void noMatchingEvent() {
        // given
        VisitingDate date = VisitingDate.from(25);
        Orders orders = Orders.from(
                List.of(
                        OrderItem.of(MenuItem.시저샐러드.name(), 1)));

        List<MatchingEvent> events = EventDetail.findByCondition(date, orders);
        MatchingEvents matchingEvents = MatchingEvents.from(events);

        // when, then
        assertThat(matchingEvents.provideMatchingEvents().size()).isEqualTo(0);
        assertThat(matchingEvents.containsGiveAway()).isFalse();
        assertThat(matchingEvents.calculateTotalDiscountAmount()).isEqualTo(0);
        assertThat(matchingEvents.findBadgeName()).isEqualTo(BadgeCondition.NONE.getBadgeName());
        assertThat(matchingEvents.calculateTotalBenefitAmount()).isEqualTo(0);
    }

    @DisplayName("해당되는 이벤트 찾아서 결과 출력")
    @Test
    void matchingEvent() {
        // given
        VisitingDate date = VisitingDate.from(25);
        Orders orders = Orders.from(
                List.of(
                        OrderItem.of(MenuItem.크리스마스파스타.name(), 1),
                        OrderItem.of(MenuItem.샴페인.name(), 1)));

        List<MatchingEvent> events = EventDetail.findByCondition(date, orders);
        MatchingEvents matchingEvents = MatchingEvents.from(events);

        // when, then
        assertThat(matchingEvents.provideMatchingEvents().size()).isEqualTo(2);
        assertThat(matchingEvents.containsGiveAway()).isFalse();
        assertThat(matchingEvents.calculateTotalDiscountAmount()).isEqualTo(4400);
        assertThat(matchingEvents.findBadgeName()).isEqualTo(BadgeCondition.NONE.getBadgeName());
        assertThat(matchingEvents.calculateTotalBenefitAmount()).isEqualTo(4400);
    }
}