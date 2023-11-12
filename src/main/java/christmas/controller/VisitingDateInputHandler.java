package christmas.controller;

import christmas.domain.visitingDate.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class VisitingDateInputHandler {
    private final InputView inputView;
    private final OutputView outputView;

    private VisitingDateInputHandler(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static VisitingDateInputHandler of(InputView inputView, OutputView outputView) {
        return new VisitingDateInputHandler(inputView, outputView);
    }

    public VisitingDate createVisitingDate() {
        while (true) {
            try {
                int input = inputView.readVisitingDate();
                return VisitingDate.from(input);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
