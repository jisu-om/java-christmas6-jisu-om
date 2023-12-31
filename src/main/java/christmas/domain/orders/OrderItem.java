package christmas.domain.orders;

import christmas.utils.OrderItemValidationUtils;

public class OrderItem {
    private final MenuItem menuItem;
    private final int quantity;

    private OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public static OrderItem of(String menuName, int quantity) {
        OrderItemValidationUtils.validatePositive(quantity);
        MenuItem validMenuItem = convertToMenu(menuName);
        return new OrderItem(validMenuItem, quantity);
    }

    private static MenuItem convertToMenu(String menuName) {
        return MenuItem.findByName(menuName);
    }

    public MenuCategory convertToCategory() {
        return menuItem.getCategory();
    }

    public long calculateItemOriginalPrice() {
        return menuItem.getPrice() * quantity;
    }

    public MenuItem provideMenuItem() {
        return menuItem;
    }

    public String provideMenuName() {
        return menuItem.name();
    }

    public int provideQuantity() {
        return quantity;
    }
}