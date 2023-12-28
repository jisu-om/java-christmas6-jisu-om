package christmas.domain.orders;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;

import static christmas.exception.ErrorMessage.*;

public class OrderItem {
    private final Menu menu;
    private final Quantity quantity;

    private OrderItem(Menu menu, Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderItem of(String menuName, int quantity) {
        Menu menu = Menu.findMenuByName(menuName);
        Quantity validQuantity = Quantity.from(quantity);
        return new OrderItem(menu, validQuantity);
    }

    public int calculatePrice() {
        return menu.getPrice() * quantity.getQuantity();
    }

    public boolean isMenuType(MenuType type) {
        return menu.isMenuType(type);
    }

    public Menu provideMenu() {
        return menu;
    }

    public String provideMenuName() {
        return menu.name();
    }

    public int provideQuantity() {
        return quantity.getQuantity();
    }
}
