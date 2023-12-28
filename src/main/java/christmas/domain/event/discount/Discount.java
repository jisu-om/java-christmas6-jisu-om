package christmas.domain.event.discount;

import christmas.domain.event.Event;
import christmas.domain.visitingDate.VisitingDate;

public abstract class Discount implements Event {

    protected final VisitingDate date;

    protected Discount(VisitingDate date) {
        this.date = date;
    }
}
