package christmas.domain.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class MatchingEventTest {
    @DisplayName("matchingEvent 생성")
    @Test
    void create() {
        // given
        MatchingEvent event = MatchingEvent.of(EventDetail.CHRISTMAS_D_DAY, 1000);

        // when, then
        Assertions.assertThat(event.isGiveAway()).isFalse();
    }
}