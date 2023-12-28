package christmas.domain.badge;

public class BadgeFinder {
    public static Badge find(int totalBenefitAmount) {
        return Badge.findBadgeByCondition(totalBenefitAmount);
    }
}
