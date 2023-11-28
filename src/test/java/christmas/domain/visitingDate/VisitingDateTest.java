package christmas.domain.visitingDate;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class VisitingDateTest {
    @DisplayName("VisitingDate 정상 생성 테스트")
    @Test
    void create() {
        VisitingDate date = VisitingDate.from(1);
        assertThat(date).isNotNull();
    }

    @ParameterizedTest(name = "[{index}] {0} 입력 시 예외 발생한다")
    @ValueSource(ints = {-100, 0, 32, 100})
    void exception(int element) {
        assertThatThrownBy(() -> VisitingDate.from(element))
                .isInstanceOf(IllegalArgumentException.class);
    }
}