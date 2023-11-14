package christmas.domain.badge;

import christmas.domain.badge.BadgeCondition;
import christmas.domain.event.MatchingEvents;

public class BadgeGenerator {
    public static String findBadgeName(long totalBenefitAmount) {
        return BadgeCondition.findBadgeNameByCondition(totalBenefitAmount);
    }
}