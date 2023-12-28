package christmas.domain.event;

public interface Event {
    boolean isApplied();
    int getBenefitAmount();
    String getName();
}
