package christmas;

import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = ChristMasController.of(inputView.getInstance(), outputView.getInstance());
        christmasController.run();
    }
}
