package christmas.domain.event;

import christmas.domain.menu.MenuItem;
import christmas.domain.orders.OrderItem;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.constants.DiscountConstants.GIVE_AWAY_PRICE;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MatchingEventDetailTest {
    EventDetail christmas = EventDetail.CHRISTMAS_D_DAY;
    EventDetail weekdays = EventDetail.WEEKDAYS;
    EventDetail weekends = EventDetail.WEEKENDS;
    EventDetail giveAway = EventDetail.GIVE_AWAY;

    @DisplayName("calculateBenefitAmount 테스트")
    @Test
    void calculateBenefitAmount() {
        // given
        VisitingDate date = VisitingDate.from(1); //
        OrderItem appetizer = OrderItem.of(MenuItem.시저샐러드.name(), 1);
        OrderItem main1 = OrderItem.of(MenuItem.티본스테이크.name(), 1);
        OrderItem main2 = OrderItem.of(MenuItem.해산물파스타.name(), 1);
        OrderItem dessert = OrderItem.of(MenuItem.초코케이크.name(), 1);
        OrderItem drink1 = OrderItem.of(MenuItem.레드와인.name(), 1);
        OrderItem drink2 = OrderItem.of(MenuItem.제로콜라.name(), 1);

        Orders orders = Orders.from(List.of(appetizer, main1, main2, dessert, drink1, drink2));

        // when
        long christmasBenefitAmount = christmas.calculateBenefitAmount(date, orders);
        long weekdaysBenefitAmount = weekdays.calculateBenefitAmount(date, orders);
        long weekendsBenefitAmount = weekends.calculateBenefitAmount(date, orders);
        long giveAwayBenefitAmount = giveAway.calculateBenefitAmount(date, orders);

        // then : 날짜 조건에 상관 없이 이벤트에 해당된다고 간주
        assertThat(christmasBenefitAmount).isEqualTo(1000);
        assertThat(weekdaysBenefitAmount).isEqualTo(2023);
        assertThat(weekendsBenefitAmount).isEqualTo(2023 * 2);
        assertThat(giveAwayBenefitAmount).isEqualTo(GIVE_AWAY_PRICE);
    }
}