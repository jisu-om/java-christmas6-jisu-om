package christmas.domain;

import static christmas.constants.DateConstants.EVENT_END_DATE;
import static christmas.constants.DateConstants.EVENT_START_DATE;
import static christmas.exception.ErrorMessage.INVALID_DATE;

public class VisitingDate {
    private final int date;

    private VisitingDate(int date) {
        validateDate(date);
        this.date = date;
    }

    public static VisitingDate from(int date) {
        return new VisitingDate(date);
    }

    private void validateDate(int date) {
        if (date < EVENT_START_DATE || date > EVENT_END_DATE) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }
}
