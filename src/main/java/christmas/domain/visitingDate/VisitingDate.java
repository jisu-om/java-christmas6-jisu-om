package christmas.domain.visitingDate;


import christmas.exception.ErrorMessage;

import static christmas.exception.ErrorMessage.*;

public class VisitingDate {
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int DAYS_IN_WEEK = 7;
    private static final int CHRISTMAS_DAY = 25;
    private static final int WEEKEND_FIRST_DAY_MODULO = 1;
    private static final int WEEKEND_SECOND_DAY_MODULO = 2;
    private static final int SPECIAL_DAY_MODULO = 3;
    private final int date;

    private VisitingDate(int date) {
        this.date = date;
    }

    public static VisitingDate from(int date) {
        validateDate(date);
        return new VisitingDate(date);
    }

    private static void validateDate(int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(INVALID_VISITING_DATE.getMessage());
        }
    }

    public int getBeforeDate() {
        return date - 1;
    }

    public boolean isBeforeChristmas() {
        return date <= CHRISTMAS_DAY;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return date % DAYS_IN_WEEK == WEEKEND_FIRST_DAY_MODULO || date % DAYS_IN_WEEK  == WEEKEND_SECOND_DAY_MODULO;
    }

    public boolean isSpecial() {
        return date % DAYS_IN_WEEK == SPECIAL_DAY_MODULO || date == CHRISTMAS_DAY;
    }
}