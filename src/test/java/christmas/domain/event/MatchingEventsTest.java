package christmas.domain.event;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MatchingEventsTest {
    @DisplayName("생성")
    @Test
    void create() {
        // given
        EventDetail christmas = EventDetail.CHRISTMAS_D_DAY;
        EventDetail weekdays = EventDetail.WEEKDAYS;
        EventDetail weekends = EventDetail.WEEKENDS;
        EventDetail special = EventDetail.SPECIAL;
        EventDetail giveAway = EventDetail.GIVE_AWAY;

        List<EventDetail> eventDetails1 = List.of(christmas, weekdays, special);
        List<EventDetail> eventDetails2 = List.of(weekends, giveAway);

        // when
        MatchingEvents matchingEvents1 = MatchingEvents.from(eventDetails1);
        MatchingEvents matchingEvents2 = MatchingEvents.from(eventDetails2);

        // then
        assertThat(matchingEvents1.provideMatchingEvents().size()).isEqualTo(3);
        assertThat(matchingEvents1.containsGiveAway()).isFalse();

        assertThat(matchingEvents2.provideMatchingEvents().size()).isEqualTo(2);
        assertThat(matchingEvents2.containsGiveAway()).isTrue();
    }
}