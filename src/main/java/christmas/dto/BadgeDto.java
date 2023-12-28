package christmas.dto;

import christmas.domain.badge.Badge;

public record BadgeDto(String name) {
    public static BadgeDto from(Badge badge) {
        return new BadgeDto(badge.getBadgeName());
    }
}
