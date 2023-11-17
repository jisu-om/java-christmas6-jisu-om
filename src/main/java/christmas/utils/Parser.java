package christmas.utils;

import java.util.List;

import static christmas.exception.ErrorMessage.INVALID_ORDERS;

public class Parser {
    public static Integer safeParseInt(String value, String message) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    public static List<String> safeSplit(String input, String delimiter) {
        validateEmpty(input);
        validateStartsOrEndsWithDelimiter(input, delimiter);
        return List.of(input.split(delimiter));
    }

    private static void validateEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    private static void validateStartsOrEndsWithDelimiter(String input, String delimiter) {
        if (input.startsWith(delimiter) || input.endsWith(delimiter)) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }
}