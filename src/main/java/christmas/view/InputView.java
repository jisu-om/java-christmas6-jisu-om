package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ErrorMessage;

import static christmas.exception.ErrorMessage.*;

public class InputView {
    private static final InputView instance = new InputView();
    private static final String ASK_VISITING_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    private InputView() {
    }
    public static InputView getInstance() {
        return instance;
    }

    public int readVisitingDate() {
        System.out.println(ASK_VISITING_DATE);
        String input = Console.readLine();
        return parseToInt(input);
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }
}
