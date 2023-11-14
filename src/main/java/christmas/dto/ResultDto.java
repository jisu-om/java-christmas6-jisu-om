package christmas.dto;

import java.util.List;

import static christmas.exception.ErrorMessage.INVALID_CREATION;

public class ResultDto {
    private final List<EventDetailDto> events;
    private final long originalTotalAmount;
    private final boolean containsGiveAway;
    private final long totalBenefitAmount;
    private final long totalDiscountAmount;
    private final String badgeName;

    private ResultDto(Builder builder) {
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

        public ResultDto build() {
            if (events == null || originalTotalAmount == null || containsGiveAway == null
                    || totalBenefitAmount == null || totalDiscountAmount == null || badgeName == null) {
                throw new IllegalStateException(INVALID_CREATION.getMessage());
            }
            return new ResultDto(this);
        }
    }

    public List<EventDetailDto> getEventDetails() {
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