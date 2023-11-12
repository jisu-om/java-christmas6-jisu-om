package christmas.view;

public class InputView {
    private static final InputView instance = new InputView();

    private InputView() {
    }
    public static InputView getInstance() {
        return instance;
    }


}
