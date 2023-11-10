package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class VisitingDateTest {
    @DisplayName("1 이상 31 이하이면 방문 날짜 정상 생성된다.")
    @Test
    void create() {
        // given
        int date = 1;

        // when
        VisitingDate visitingDate = VisitingDate.from(date);

        // then
        assertThat(visitingDate).isNotNull();
    }

    @ParameterizedTest(name = "[{index}] {0} 을 전달하면 방문날짜 생성 시 예외가 발생한다.")
    @ValueSource(ints = {0, -1, 32, 1000})
    void throwsException(int element) {
        assertThatThrownBy(() -> visitingDate.from(element))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
