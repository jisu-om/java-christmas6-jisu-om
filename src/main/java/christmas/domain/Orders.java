package christmas.domain;

import java.util.EnumMap;
import java.util.Map;

public class Orders {
    private final EnumMap<Menu, Integer> orders;

    private Orders(EnumMap<Menu, Integer> orders) {
        this.orders = orders;
    }

    public static Orders from(Map<String, Integer> orders) {
        validate(orders);
        //TODO Map<String, Integer> -> EnumMap<Menu, Integer> 로 변환
        EnumMap<Menu, Integer> validatedOrders = null;
        return new Orders(validatedOrders);
    }

    private static void validate(Map<String, Integer> orders) {
        //TODO
         validateExistence(orders);
         validateDuplicates(orders);
         validateOnlyDrinks(orders);
         validateQuantity(orders);
         validateTotalQuantity(orders);
    }

    private static void validateExistence(Map<String, Integer> orders) {

    }

    private static void validateDuplicates(Map<String, Integer> orders) {

    }

    private static void validateOnlyDrinks(Map<String, Integer> orders) {

    }

    private static void validateQuantity(Map<String, Integer> orders) {

    }

    private static void validateTotalQuantity(Map<String, Integer> orders) {

    }
}
