package christmas.domain.badge;

public enum BadgeCondition {
    STAR("별", 5_000L),
    TREE("트리", 10_000L),
    SANTA("산타", 20_000L),
    NONE(null, 0L);

    private final String badgeName;
    private final long condition;

    BadgeCondition(String badgeName, long condition) {
        this.badgeName = badgeName;
        this.condition = condition;
    }
}
