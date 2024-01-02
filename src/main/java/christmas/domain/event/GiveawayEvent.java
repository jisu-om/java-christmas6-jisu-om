package christmas.domain.event;

import christmas.domain.menu.Menu;
import christmas.domain.orders.Orders;

public class GiveawayEvent implements Event {
    private static final String GIVEAWAY_EVENT_NAME = "증정 이벤트";
    private static final Menu GIVEAWAY_ITEM = Menu.샴페인;
    private static final int MINIMUM_ORDER_AMOUNT = 120_000;

    private final Orders orders;

    public GiveawayEvent(Orders orders) {
        this.orders = orders;
    }

    public static String getGiveawayItemName() {
        return GIVEAWAY_ITEM.name();
    }

    @Override
    public int getBenefitAmount() {
        if (isApplied()) {
            return GIVEAWAY_ITEM.getPrice();
        }
        return 0;
    }

    @Override
    public boolean isApplied() {
        return orders.isTotalPriceMoreThan(MINIMUM_ORDER_AMOUNT);
    }

    @Override
    public String getName() {
        return GIVEAWAY_EVENT_NAME;
    }

    @Override
    public boolean isDiscount() {
        return false;
    }

    @Override
    public boolean isGiveaway() {
        return true;
    }
}
