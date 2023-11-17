package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.ChristmasController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = ChristmasController.of(InputView.getInstance(), OutputView.getInstance());
        christmasController.run();
        Console.close();
    }
}