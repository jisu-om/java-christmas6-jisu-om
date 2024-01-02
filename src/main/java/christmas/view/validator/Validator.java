package christmas.view.validator;

public class Validator {
    public static int safeParseInt(String input, String errorMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private Validator() {
    }
}
