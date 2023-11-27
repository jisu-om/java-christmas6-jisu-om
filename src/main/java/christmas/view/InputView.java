package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitingDate;
import christmas.utils.VisitingDateValidator;

public class InputView {
    private static final InputView instance = new InputView();
    private static final String VISITING_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    public int readVisitingDate() {
        System.out.println(VISITING_DATE_MESSAGE);
        String input = Console.readLine();
        return VisitingDateValidator.safeParseInt(input);
    }
}
