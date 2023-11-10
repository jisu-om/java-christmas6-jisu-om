package christmas.domain;

import static christmas.exception.ErrorMessage.INVALID_ORDER;

public class OrderItem {
    private static final int MINIMUM_QUANTITY = 1;
    private final MenuItem menuItem;
    private final int quantity;

    private OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public static OrderItem of(String menu, int quantity) {
        validatePositive(quantity);
        MenuItem validMenuItem = convertToMenu(menu);
        return new OrderItem(validMenuItem, quantity);
    }

    private static void validatePositive(int quantity) {
        //TODO validationUtils 로 빼기
        if (quantity < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static MenuItem convertToMenu(String menu) {
        //TODO Converter? Parser? ValidationUtils?
        return MenuItem.findByName(menu);
    }

    public MenuItem provideMenuItem() {
        return menuItem;
    }

    public int provideQuantity() {
        return quantity;
    }
}
