package christmas.controller;

import christmas.domain.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.function.Supplier;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    //private OrderController orderController;  //컨트롤러 추가하는 경우

    private MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static MainController create() {
        return new MainController(InputView.getInstance(), OutputView.getInstance());
    }

    public void run() {
        outputView.printStart();
        VisitingDate visitingDate = createVisitingDate();

    }

    private void initializeControllers() {
        orderController = OrderController.of(inputView, outputView);
    }


    private VisitingDate createVisitingDate() {
        return readUserInput(() -> {
            int input = inputView.readVisitingDate();
            return VisitingDate.from(input);
        });
    }

    private Orders createOrders() {
        return readUserInput(() -> {

        });
    }

    private <T> T readUserInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
