package christmas.domain.badge;

import java.util.Arrays;

public enum BadgeCondition {
    NONE("없음", 0L),
    STAR("별", 5_000L),
    TREE("트리", 10_000L),
    SANTA("산타", 20_000L);

    private final String badgeName;
    private final long minCondition;

    BadgeCondition(String badgeName, long minCondition) {
        this.badgeName = badgeName;
        this.minCondition = minCondition;
    }

    public static BadgeCondition findBadgeByCondition(long totalBenefitAmount) {
        return Arrays.stream(BadgeCondition.values())
                .filter(badge -> totalBenefitAmount >= badge.getMinCondition())
                .findFirst()
                .orElse(NONE);
    }

    public String getBadgeName() {
        return badgeName;
    }

    public long getMinCondition() {
        return minCondition;
    }
}