package christmas.domain.visitingDate;

import static christmas.constants.DateConstants.EVENT_END;
import static christmas.constants.DateConstants.EVENT_START;
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
        if (date < EVENT_START || date > EVENT_END) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public int provideDate() {
        return date;
    }
}