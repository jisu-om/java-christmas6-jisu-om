package christmas.dto;

import christmas.domain.event.EventDetail;
import christmas.domain.event.MatchingEvents;
import christmas.domain.orders.Orders;
import christmas.domain.visitingDate.VisitingDate;
import christmas.service.BadgeGenerator;

import java.util.List;

import static christmas.exception.ErrorMessage.INVALID_CREATION;

public class MatchingEventsDto {
    private final List<EventDetailDto> events;
    private final long originalTotalAmount;
    private final boolean containsGiveAway;
    private final long totalBenefitAmount;
    private final long totalDiscountAmount;
    private final String badgeName;

    private MatchingEventsDto(Builder builder) {
        this.events = builder.events;
        this.originalTotalAmount = builder.originalTotalAmount;
        this.containsGiveAway = builder.containsGiveAway;
        this.totalBenefitAmount = builder.totalBenefitAmount;
        this.totalDiscountAmount = builder.totalDiscountAmount;
        this.badgeName = builder.badgeName;
    }

    public static class Builder {
        private List<EventDetailDto> events;
        private Long originalTotalAmount;
        private Boolean containsGiveAway;
        private Long totalBenefitAmount;
        private Long totalDiscountAmount;
        private String badgeName;

        public Builder events(List<EventDetailDto> events) {
            this.events = events;
            return this;
        }

        public Builder originalTotalAmount(long originalTotalAmount) {
            this.originalTotalAmount = originalTotalAmount;
            return this;
        }

        public Builder containsGiveAway(boolean containsGiveAway) {
            this.containsGiveAway = containsGiveAway;
            return this;
        }

        public Builder totalBenefitAmount(long totalBenefitAmount) {
            this.totalBenefitAmount = totalBenefitAmount;
            return this;
        }

        public Builder totalDiscountAmount(long totalDiscountAmount) {
            this.totalDiscountAmount = totalDiscountAmount;
            return this;
        }

        public Builder badgeName(String badgeName) {
            this.badgeName = badgeName;
            return this;
        }

        public MatchingEventsDto build() {
            if (events == null || originalTotalAmount == null || containsGiveAway == null
                    || totalBenefitAmount == null || totalDiscountAmount == null || badgeName == null) {
                throw new IllegalStateException(INVALID_CREATION.getMessage());
            }
            return new MatchingEventsDto(this);
        }
    }

    public List<EventDetailDto> getEvents() {
        return events;
    }

    public long getOriginalTotalAmount() {
        return originalTotalAmount;
    }

    public boolean isContainsGiveAway() {
        return containsGiveAway;
    }

    public long getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public long getExpectedTotalAmount() {
        return originalTotalAmount - totalDiscountAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }
}