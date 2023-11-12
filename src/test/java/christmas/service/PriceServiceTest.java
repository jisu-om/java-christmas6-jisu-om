package christmas.service;

import christmas.domain.menu.MenuItem;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PriceServiceTest {
    String VALID_APPETIZER = "양송이수프";
    String VALID_DRINK = "제로콜라";
    int VALID_QUANTITY1 = 1;
    int VALID_QUANTITY2 = 2;

    @DisplayName("주문금액 계산")
    @Test
    void calculate_originalPrice() {
        // given
        VisitingDate visitingDate = VisitingDate.from(1);
//        OrderItem orderItem1 = OrderItem.of(VALID_APPETIZER, VALID_QUANTITY2);
//        OrderItem orderItem2 = OrderItem.of(VALID_DRINK, VALID_QUANTITY1);
//        Orders orders = Orders.from(List.of(orderItem1, orderItem2));
//        long actualPrice = MenuItem.valueOf(VALID_APPETIZER).getPrice() * VALID_QUANTITY2
//                + MenuItem.valueOf(VALID_DRINK).getPrice() * VALID_QUANTITY1;
//
//        EventFinder eventService = EventFinder.of(visitingDate, orders);
//        MatchingEvents events = eventService.findMatchingEvents();
//        EventBenefitCalculator priceService = EventBenefitCalculator.of(orders, events);
//
//        // when
//        PricingInfo pricingInfo = priceService.providePricingInfo();
//
//        // then
//        Assertions.assertThat(pricingInfo.provideOriginalPrice()).isEqualTo(actualPrice);
    }
}
