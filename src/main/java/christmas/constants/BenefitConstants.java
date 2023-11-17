package christmas.constants;

import christmas.domain.orders.MenuItem;

public final class BenefitConstants {
    public static final long BASE_DISCOUNT = 1_000L;
    public static final long CHRISTMAS_RATE = 100L;
    public static final long DAILY_RATE = 2_023L;
    public static final long BASE_PRICE_CONDITION = 10_000L;
    public static final long GIVE_AWAY_PRICE_CONDITION = 120_000L;
    public static final long GIVE_AWAY_PRICE = MenuItem.샴페인.getPrice();
    public static final String GIVE_AWAY_ITEM = MenuItem.샴페인.name();
    public static final int GIVE_AWAY_QUANTITY = 1;
}