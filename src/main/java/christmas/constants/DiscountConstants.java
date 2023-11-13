package christmas.constants;

import christmas.domain.menu.MenuItem;

public final class DiscountConstants {
    public static final long BASE_DISCOUNT = 1_000L;
    public static final long CHRISTMAS_RATE = 100L;
    public static final long DAILY_RATE = 2_023L;
    public static final long GIVE_AWAY_PRICE = MenuItem.샴페인.getPrice();
    public static final String GIVE_AWAY_ITEM = MenuItem.샴페인.name();
}