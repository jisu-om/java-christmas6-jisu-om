package christmas.controller;


import christmas.domain.visitingDate.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;

class VisitingDateInputHandlerTest {
    @DisplayName("createVisitingDate 정상 테스트")
    @Test
    void createVisitingDate_o() {
        // given
        VisitingDateInputHandler visitingDateInputHandler = VisitingDateInputHandler.of(InputView.getInstance(), OutputView.getInstance());
        command("30");

        // when
        VisitingDate date = visitingDateInputHandler.createVisitingDate();

        // then
        assertThat(date).isNotNull();
    }

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }
}