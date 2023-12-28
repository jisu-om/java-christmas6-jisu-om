package christmas.dto;

import christmas.domain.event.Event;

public record EventDto(String name, int benefitAmount) {
    public static EventDto from(Event event) {
        return new EventDto(event.getName(), event.getBenefitAmount());
    }
}
