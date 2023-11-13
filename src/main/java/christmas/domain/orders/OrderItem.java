package christmas.domain.orders;

import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuItem;
import christmas.validationUtils.OrderItemValidationUtils;

public class OrderItem {
    private final MenuItem menuItem;
    private final int quantity;

    private OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public static OrderItem of(String menu, int quantity) {
        OrderItemValidationUtils.validatePositive(quantity);
        MenuItem validMenuItem = convertToMenu(menu);
        return new OrderItem(validMenuItem, quantity);
    }

    private static MenuItem convertToMenu(String menu) {
        return MenuItem.findByName(menu);
    }

    public MenuCategory convertToCategory() {
        return menuItem.getCategory();
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

    public long provideItemOriginalPrice() {
        return menuItem.getPrice() * quantity;
    }
}