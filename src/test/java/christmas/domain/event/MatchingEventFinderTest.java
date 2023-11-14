package christmas.domain.event;

import christmas.domain.event.EventFinder;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchingEventFinderTest {
    String VALID_APPETIZER = "양송이수프";
    String VALID_DRINK = "제로콜라";
    int VALID_QUANTITY1 = 1;
    int VALID_QUANTITY2 = 2;

    @DisplayName("날짜, 주문금액에 해당하는 혜택내역 계산")
    @Test
    void generate_events() {
        // given
        VisitingDate date = VisitingDate.from(1);
        OrderItem orderItem1 = OrderItem.of(VALID_APPETIZER, VALID_QUANTITY2);
        OrderItem orderItem2 = OrderItem.of(VALID_DRINK, VALID_QUANTITY1);
        Orders orders = Orders.from(List.of(orderItem1, orderItem2));

        //when
        MatchingEvents matchingEvents = EventFinder.findMatchingEvents(date, orders);

        //then  //1일, 15000원 - 1000(크리스마스 디데이 할인)

    }
}