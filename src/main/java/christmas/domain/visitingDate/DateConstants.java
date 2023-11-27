package christmas.domain.visitingDate;

public enum DateConstants {
    DAYS_IN_WEEK(7),
    CHRISTMAS_DAY(25),
    WEEKEND_FIRST_DAY_MODULO(1),
    WEEKEND_SECOND_DAY_MODULO(2),
    SPECIAL_DAY_MODULO(3);

    private final int value;

    DateConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
