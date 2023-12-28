package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.MainController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(InputView.getInstance(), OutputView.getInstance());
        mainController.run();
        Console.close();
    }
}
