package christmas.utils;

import static christmas.exception.ErrorMessage.INVALID_VISITING_DATE;

public class VisitingDateValidator {
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;

    public static void validateDate(int date) {
        if (date < START_DATE || date > END_DATE) {
            throw new IllegalArgumentException(INVALID_VISITING_DATE.getMessage());
        }
    }
}
