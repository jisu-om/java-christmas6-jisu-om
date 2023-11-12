package christmas.service;

import christmas.domain.badge.BadgeCondition;
import christmas.domain.event.MatchingEvents;

public class BadgeGenerator {
    public static String findBadgeName(MatchingEvents events) {
        long totalBenefitAmount = BenefitCalculator.calculateTotalBenefitAmount(events);
        return BadgeCondition.findBadgeNameByCondition(totalBenefitAmount);
    }
}
