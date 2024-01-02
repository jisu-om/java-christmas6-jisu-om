package christmas.view.validator;

import org.junit.platform.commons.util.StringUtils;

import java.util.List;

import static christmas.exception.ErrorMessage.INVALID_ORDER;

public class OrdersValidator {
    private static final int ORDER_PAIR_SIZE = 2;

    private OrdersValidator() {
    }

    public static List<String> safeSplit(String input, String delimiter) {
        validateEmpty(input);
        validateStartsOrEndsWithDelimiter(input, delimiter);
        return List.of(input.split(delimiter));
    }

    private static void validateEmpty(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static void validateStartsOrEndsWithDelimiter(String input, String delimiter) {
        if (input.startsWith(delimiter) || input.endsWith(delimiter)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public static void validatePair(List<String> pair) {
        if (pair.size() != ORDER_PAIR_SIZE) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
