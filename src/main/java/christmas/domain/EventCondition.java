package christmas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static christmas.constants.DateConstants.*;

public enum EventCondition {
    CHRISTMAS_D_DAY(IntStream.rangeClosed(EVENT_START, CHRISTMAS_EVENT_END)
                    .boxed()
                    .collect(Collectors.toList()), 10_000),
    WEEKDAYS(IntStream.rangeClosed(EVENT_START, EVENT_END)
                    .filter(i -> i % 7 >= 3 || i % 7 == 0).boxed()
                    .toList(), 10_000),
    WEEKENDS(IntStream.rangeClosed(EVENT_START, EVENT_END)
                    .filter(i -> i % 7 == 1 || i % 7 == 2).boxed()
                    .toList(), 10_000),
    SPECIAL(List.of(3, 10, 17, 24, 25, 31), 10_000),
    GIVE_AWAY(IntStream.rangeClosed(EVENT_START, EVENT_END)
                    .boxed()
                    .toList(), 120_000),
    NONE(Collections.emptyList(), 0);

    private final List<Integer> dateCondition;
    private final long priceCondition;

    EventCondition(List<Integer> dateCondition, long priceCondition) {
        this.dateCondition = dateCondition;
        this.priceCondition = priceCondition;
    }

    public EventCondition findByDateAndPriceCondition(VisitingDate date, long originalPrice) {
        return Arrays.stream(EventCondition.values())
                .filter(condition -> condition.dateCondition.contains(date.getDate()))
                .filter(condition -> originalPrice >= condition.priceCondition)
                .findFirst()
                .orElse(NONE);
    }
}
