package christmas.service;

import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BadgeGeneratorTest {
    String VALID_APPETIZER = "양송이수프";
    String VALID_MAIN = "티본스테이크";
    String VALID_DRINK = "제로콜라";
    int VALID_QUANTITY1 = 1;
    int VALID_QUANTITY2 = 2;
    int VALID_QUANTITY5 = 5;

    @DisplayName("혜택금액에 따른 이벤트 배지 생성")
    @Test
    void create_badge() {
        // given
//        VisitingDate visitingDate = VisitingDate.from(2);  //메인메뉴 할인(메인메뉴 개수 * 2023원), 크리스마스 디데이 할인(1100원), 증정이벤트(총주문금액 12만원 이상인 경우)
//        OrderItem orderItem1 = OrderItem.of(VALID_APPETIZER, VALID_QUANTITY5);  //양송이수프 6000 * 5
//        OrderItem orderItem2 = OrderItem.of(VALID_MAIN, VALID_QUANTITY2);  //티본스테이크 55000 * 2
//        OrderItem orderItem3 = OrderItem.of(VALID_DRINK, VALID_QUANTITY1);  //제로콜라 3000 * 1
//        Orders orders = Orders.from(List.of(orderItem1, orderItem2, orderItem3));  //총주문금액 : 14만 3천원
//
//        EventFinder eventService = EventFinder.of(visitingDate, orders);
//        Events events = eventService.findMatchingEvents();
//        EventBenefitCalculator priceService = EventBenefitCalculator.of(orders, events);
//        PricingInfo pricingInfo = priceService.providePricingInfo();
//        long originalPrice = pricingInfo.provideOriginalPrice();  //143_000
//
//        // when
//        BadgeGenerator badgeService = BadgeGenerator.from(originalPrice);
//
//        // then
//        Badge badge = badgeService.proviceBadge();
//        assertThat(badge.name()).isEqualTo(Badge.산타);
    }
}
