package christmas.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static christmas.exception.ErrorMessage.INVALID_ORDERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InputViewTest {
    InputStream originalIn = System.in;
    private InputView inputView = InputView.getInstance();

    @AfterEach
    void afterEach() {
        System.setIn(originalIn);
    }

    @DisplayName("readOrders() 정상 수행")
    @Test
    void readOrders() {
        // given

        // when

        // then

    }

    @DisplayName("',,,'를 입력하면 readOrders() 예외가 발생한다.")
    @Test
    void readOrders_exception1() {
        final byte[] buf = String.join("\n", ",,,").getBytes();
        System.setIn(new ByteArrayInputStream(buf));

        try {
            inputView.readOrders();
        } catch (IllegalArgumentException e) {
            assertEquals(INVALID_ORDERS.getMessage(), e.getMessage());
        }
    }

    @Test
    void split() {
        String ITEM_DELIMITER = ",";
        String ITEM_QUANTITY_DELIMITER = "-";

        String validSentence = "양송이수프-1,레드와인-3";
        String[] value1 = validSentence.split(ITEM_DELIMITER);
        List<String> value12 = List.of(value1);
        assertThat(value12.size()).isEqualTo(2);

        List<String> value1ToList = List.of(value1);
        for (String element : value1ToList) {
            System.out.println(element.split(ITEM_QUANTITY_DELIMITER).length);
        }
    }
}