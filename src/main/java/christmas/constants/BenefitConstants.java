package christmas.constants;

import christmas.domain.menu.Menu;

public class BenefitConstants {
    public static final long ORDER_MINIMUM = 10000;
    public static final long GIVE_AWAY_MINIMUM = 120000;
    public static final long CHRISTMAS_D_DAY_BASE_BENEFIT = 1000;
    public static final long CHRISTMAS_D_DAY_DAILY_BENEFIT = 100;
    public static final long WEEKDAY_BENEFIT_UNIT = 2023;
    public static final long WEEKEND_BENEFIT_UNIT = 2023;
    public static final long SPECIAL_BENEFIT = 1000;
    public static final Menu GIVE_AWAY_PRODUCT = Menu.샴페인;
    public static final String GIVE_AWAY_PRODUCT_NAME = Menu.샴페인.name();
    public static final long GIVE_AWAY_BENEFIT_AMOUNT = GIVE_AWAY_PRODUCT.getPrice();
}
