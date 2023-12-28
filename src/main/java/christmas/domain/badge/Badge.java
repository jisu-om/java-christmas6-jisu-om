package christmas.domain.badge;

import java.util.Arrays;

import static christmas.exception.ErrorMessage.INVALID_BADGE_FINDER_PARAMETER;

public enum Badge {
    NONE("", 0L, 5_000),
    STAR("별", 5_000, 10_000),
    TREE("트리", 10_000, 20_000),
    SANTA("산타", 20_000, Long.MAX_VALUE);

    private final String badgeName;
    private final long minimumBenefitAmount;
    private final long maximumBenefitAmount;

    Badge(String badgeName, long minimumBenefitAmount, long maximumBenefitAmount) {
        this.badgeName = badgeName;
        this.minimumBenefitAmount = minimumBenefitAmount;
        this.maximumBenefitAmount = maximumBenefitAmount;
    }

    public static Badge findBadgeByCondition(long totalBenefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> totalBenefitAmount >= badge.getMinimumBenefitAmount()
                        && totalBenefitAmount < badge.getMaximumBenefitAmount())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(INVALID_BADGE_FINDER_PARAMETER.getMessage()));
    }

    public long getMinimumBenefitAmount() {
        return minimumBenefitAmount;
    }

    public long getMaximumBenefitAmount() {
        return maximumBenefitAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
