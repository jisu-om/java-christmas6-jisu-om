package christmas.domain.visitingDate;


import static christmas.domain.visitingDate.DateConstants.*;

public class VisitingDate {
    private final int date;

    private VisitingDate(int date) {
        this.date = date;
    }

    public static VisitingDate from(int date) {
        VisitingDateValidator.validateDate(date);
        return new VisitingDate(date);
    }

    public boolean isBeforeChristmas() {
        return date <= CHRISTMAS_DAY.getValue();
    }

    public boolean isSpecial() {
        return date % DAYS_IN_WEEK.getValue() == SPECIAL_DAY_MODULO.getValue() || date == CHRISTMAS_DAY.getValue();
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return date % DAYS_IN_WEEK.getValue() == WEEKEND_FIRST_DAY_MODULO.getValue()
                || date % DAYS_IN_WEEK.getValue()  == WEEKEND_SECOND_DAY_MODULO.getValue();
    }

    public int getChristmasDDayBenefitDate() {
        return date - 1;
    }
}