package christmas.domain;

import christmas.constants.DateConstants;
import christmas.exception.ErrorMessage;

import static christmas.constants.DateConstants.*;
import static christmas.exception.ErrorMessage.*;

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
