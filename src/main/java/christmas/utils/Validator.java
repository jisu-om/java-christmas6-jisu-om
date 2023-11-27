package christmas.utils;

import static christmas.exception.ErrorMessage.INVALID_VISITING_DATE;

public class Validator {
    public static int safeParseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_VISITING_DATE.getMessage());
        }
    }
}
