package christmas.domain.menu;

import java.util.Arrays;

import static christmas.domain.menu.MenuType.*;
import static christmas.exception.ErrorMessage.INVALID_ORDER;

public enum Menu {
    양송이수프(APPETIZER, 6_000),
    타파스(APPETIZER, 5_500),
    시저샐러드(APPETIZER, 8_000),
    티본스테이크(MAIN, 55_000),
    바비큐립(MAIN, 54_000),
    해산물파스타(MAIN, 35_000),
    크리스마스파스타(MAIN, 25_000),
    초코케이크(DESSERT, 15_000),
    아이스크림(DESSERT, 5_000),
    제로콜라(DRINK, 3_000),
    레드와인(DRINK, 60_000),
    샴페인(DRINK, 25_000);

    private final MenuType type;
    private final long price;

    Menu(MenuType type, long price) {
        this.type = type;
        this.price = price;
    }

    public static Menu findMenuByName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isEqualName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER.getMessage()));
    }

    private boolean isEqualName(String name) {
        return this.name().equals(name);
    }

    public boolean isMenuType(MenuType menuType) {
        return type == menuType;
    }

    public long getPrice() {
        return price;
    }
}
