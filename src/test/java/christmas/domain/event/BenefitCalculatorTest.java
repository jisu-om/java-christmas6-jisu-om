package christmas.domain.event;

import christmas.domain.menu.MenuItem;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.constants.DiscountConstants.GIVE_AWAY_PRICE;
import static org.assertj.core.api.Assertions.*;

class BenefitCalculatorTest {
    @DisplayName("정상 작동 테스트")
    @Test
    void test() {
        // given
        VisitingDate date = VisitingDate.from(1);

        OrderItem appetizer = OrderItem.of(MenuItem.시저샐러드.name(), 1);
        OrderItem main1 = OrderItem.of(MenuItem.티본스테이크.name(), 1);
        OrderItem main2 = OrderItem.of(MenuItem.해산물파스타.name(), 1);
        OrderItem dessert = OrderItem.of(MenuItem.초코케이크.name(), 1);
        OrderItem drink1 = OrderItem.of(MenuItem.레드와인.name(), 1);
        OrderItem drink2 = OrderItem.of(MenuItem.제로콜라.name(), 1);

        Orders orders = Orders.from(List.of(appetizer, main1, main2, dessert, drink1, drink2));
        MatchingEvents matchingEvents = EventFinder.findMatchingEvents(date, orders);
        BenefitCalculator benefitCalculator = BenefitCalculator.of(date, orders, matchingEvents);

        // when, then
        assertThat(benefitCalculator.calculateTotalBenefitAmount()).isEqualTo(1000 + 2023 * 2 + GIVE_AWAY_PRICE);
    }
}