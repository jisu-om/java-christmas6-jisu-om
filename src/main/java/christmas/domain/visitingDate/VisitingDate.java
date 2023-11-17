package christmas.domain.visitingDate;

import java.util.List;

import static christmas.domain.constants.DateConstants.*;
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

    public boolean isChristmasEventActive() {
        return date <= CHRISTMAS_EVENT_END;
    }

    public boolean isWeekday() {
        return date % 7 >= 3 || date % 7 == 0;
    }

    public boolean isWeekend() {
        return date % 7 == 1 || date % 7 == 2;
    }

    public boolean isSpecialDay() {
        return List.of(3, 10, 17, 24, 25, 31).contains(date);
    }

    public int provideDate() {
        return date;
    }
}