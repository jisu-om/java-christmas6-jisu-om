package christmas.service;

import christmas.domain.badge.BadgeCondition;

public class BadgeGenerator {
    public static String findBadgeName(long totalBenefitAmount) {
        return BadgeCondition.findBadgeNameByCondition(totalBenefitAmount);
    }
}
