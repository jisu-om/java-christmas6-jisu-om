package christmas.domain.event;


import christmas.domain.menu.MenuItem;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MatchingEventsTest {
    @DisplayName("생성")
    @Test
    void create() {
        // given
        VisitingDate date = VisitingDate.from(25);
        Orders orders = Orders.from(
                List.of(
                        OrderItem.of(MenuItem.시저샐러드.name(), 1),
                        OrderItem.of(MenuItem.제로콜라.name(), 2)));
        EventDetail christmas = EventDetail.CHRISTMAS_D_DAY;
        EventDetail weekdays = EventDetail.WEEKDAYS;
        EventDetail weekends = EventDetail.WEEKENDS;
        EventDetail special = EventDetail.SPECIAL;
        EventDetail giveAway = EventDetail.GIVE_AWAY;

        MatchingEvent christmasEvent = MatchingEvent.of(christmas);
        MatchingEvent christmasEvent = MatchingEvent.of(EventDetail.CHRISTMAS_D_DAY, christmas.calculateBenefitAmount(date, orders));

        // when
        MatchingEvents matchingEvents = MatchingEvents.from(christmasEvent, );

        // then
        assertThat(matchingEvents.provideMatchingEvents().size()).isEqualTo(3);
        assertThat(matchingEvents.containsGiveAway()).isFalse();

        assertThat(matchingEvents2.provideMatchingEvents().size()).isEqualTo(2);
        assertThat(matchingEvents2.containsGiveAway()).isTrue();
    }
}