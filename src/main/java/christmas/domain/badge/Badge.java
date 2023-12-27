package christmas.domain.badge;

import java.util.Arrays;

public enum Badge {
    NONE(null, 0L, 5_000),
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
                .get();
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
