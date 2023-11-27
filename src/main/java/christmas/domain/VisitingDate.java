package christmas.domain;


import christmas.utils.VisitingDateValidator;

public class VisitingDate {
    private final int date;

    private VisitingDate(int date) {
        this.date = date;
    }

    public static VisitingDate from(int date) {
        VisitingDateValidator.validateDate(date);
        return new VisitingDate(date);
    }
}
