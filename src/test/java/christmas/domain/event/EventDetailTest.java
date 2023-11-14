package christmas.domain.event;

import christmas.domain.menu.MenuItem;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.domain.event.EventDetail.*;
import static org.assertj.core.api.Assertions.*;

class EventDetailTest {
    @DisplayName("findByCondition 테스트")
    @Test
    void findByCondition() {
        // given
        VisitingDate date = VisitingDate.from(1);
        Orders orders = Orders.from(
                List.of(OrderItem.of(MenuItem.시저샐러드.name(), 1),
                        OrderItem.of(MenuItem.바비큐립.name(), 1),
                        OrderItem.of(MenuItem.초코케이크.name(), 1),
                        OrderItem.of(MenuItem.레드와인.name(), 1)));

        // when, then
        List<MatchingEvent> events = EventDetail.findByCondition(date, orders);
        assertThat(events.size()).isEqualTo(3);
    }
}