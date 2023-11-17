package christmas.domain.orders;

import java.util.Arrays;

import static christmas.domain.orders.MenuCategory.*;
import static christmas.exception.ErrorMessage.INVALID_ORDERS;

public enum MenuItem {
    양송이수프(APPETIZER, 6_000L),
    타파스(APPETIZER, 5_500L),
    시저샐러드(APPETIZER, 8_000L),
    티본스테이크(MAIN, 55_000L),
    바비큐립(MAIN, 54_000L),
    해산물파스타(MAIN, 35_000L),
    크리스마스파스타(MAIN, 25_000L),
    초코케이크(DESSERT, 15_000L),
    아이스크림(DESSERT, 5_000L),
    제로콜라(DRINK, 3_000L),
    레드와인(DRINK, 60_000L),
    샴페인(DRINK, 25_000L);

    private final MenuCategory category;
    private final long price;

    MenuItem(MenuCategory category, long price) {
        this.category = category;
        this.price = price;
    }

    public static MenuItem findByName(String value) {
        return Arrays.stream(MenuItem.values())
                .filter(item -> item.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDERS.getMessage()));
    }

    public MenuCategory getCategory() {
        return category;
    }

    public long getPrice() {
        return price;
    }
}