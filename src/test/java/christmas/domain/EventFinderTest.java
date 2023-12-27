package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


class EventFinderTest {
    @DisplayName("생성 테스트")
    @Test
    void create() {
        // given
        int date1 = 10;  //special, weekday, christmasDday, giveAway
        int date2 = 19;  //weekday, christmasDday, giveAway
        int date3 = 22;  //weekend, christmasDday, giveAway
        int date4 = 31;  //weekday, special, giveAway

        OrderItem appetizer = OrderItem.of(Menu.시저샐러드.name(), 1);
        OrderItem main = OrderItem.of(Menu.바비큐립.name(), 2);
        OrderItem dessert = OrderItem.of(Menu.아이스크림.name(), 3);

        Orders orders = Orders.from(List.of(appetizer, main, dessert));
        VisitingDate visitingDate1 = VisitingDate.from(date1);
        VisitingDate visitingDate2 = VisitingDate.from(date2);
        VisitingDate visitingDate3 = VisitingDate.from(date3);
        VisitingDate visitingDate4 = VisitingDate.from(date4);

        // when
        EventFinder reservation1 = EventFinder.of(visitingDate1, orders);
        EventFinder reservation2 = EventFinder.of(visitingDate2, orders);
        EventFinder reservation3 = EventFinder.of(visitingDate3, orders);
        EventFinder reservation4 = EventFinder.of(visitingDate4, orders);

        MatchingEvents matchingEvents1 = reservation1.createMatchingEvents();
        MatchingEvents matchingEvents2 = reservation2.createMatchingEvents();
        MatchingEvents matchingEvents3 = reservation3.createMatchingEvents();
        MatchingEvents matchingEvents4 = reservation4.createMatchingEvents();

        // then
        assertThat(matchingEvents1.getEvents().size()).isEqualTo(4);
        assertThat(matchingEvents2.getEvents().size()).isEqualTo(3);
        assertThat(matchingEvents3.getEvents().size()).isEqualTo(3);
        assertThat(matchingEvents4.getEvents().size()).isEqualTo(3);

        assertThat(matchingEvents1.calculateTotalBenefitAmount()).isEqualTo(33969L);
    }
}