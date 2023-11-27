package christmas.domain;

import christmas.utils.OrderItemValidator;

public class OrderItem {
    private final Menu menu;
    private final int quantity;

    private OrderItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderItem of(String menuName, int quantity) {
        OrderItemValidator.validatePositive(quantity);
        Menu menu = Menu.findMenuByName(menuName);
        return new OrderItem(menu, quantity);
    }

    public boolean isEqualMenuType(MenuType type) {
        return menu.isEqualMenuType(type);
    }

    public Menu provideMenu() {
        return menu;
    }

    public int provideQuantity() {
        return quantity;
    }
}
