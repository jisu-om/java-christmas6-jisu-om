package christmas.domain.orders;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;

public class OrderItem {
    private final Menu menu;
    private final int quantity;

    private OrderItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderItem of(String menuName, int quantity) {
        OrderItemValidator.validatePositive(quantity);
        OrderItemValidator.validateSize(quantity);
        Menu menu = Menu.findMenuByName(menuName);
        return new OrderItem(menu, quantity);
    }

    public long calculatePrice() {
        return menu.getPrice() * quantity;
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
        return quantity;
    }
}
