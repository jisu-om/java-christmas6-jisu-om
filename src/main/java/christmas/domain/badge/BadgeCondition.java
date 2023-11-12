package christmas.domain.badge;

import java.util.Arrays;

public enum BadgeCondition {
    //TODO 문자열 -> 상수로 변환 필요 ??
    STAR("별", 5_000L, 10_000L),
    TREE("트리", 10_000L, 20_000L),
    SANTA("산타", 20_000L, Long.MAX_VALUE),
    NONE("없음", 0L, 5_000L);

    private final String badgeName;
    private final long minCondition;
    private final long maxCondition;

    BadgeCondition(String badgeName, long minCondition, long maxCondition) {
        this.badgeName = badgeName;
        this.minCondition = minCondition;
        this.maxCondition = maxCondition;
    }

    public static String findBadgeNameByCondition(long totalBenefitAmount) {
        return Arrays.stream(BadgeCondition.values())
                .filter(badge -> totalBenefitAmount >= badge.getMinCondition() && totalBenefitAmount < badge.getMaxCondition())
                .map(BadgeCondition::getBadgeName)
                .findFirst()
                .orElse(NONE.getBadgeName());
    }

    public String getBadgeName() {
        return badgeName;
    }

    public long getMinCondition() {
        return minCondition;
    }

    public long getMaxCondition() {
        return maxCondition;
    }
}
