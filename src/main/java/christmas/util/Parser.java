package christmas.util;

import christmas.constant.Message.ExceptionMessage;

public class Parser {
    private Parser() {
    }

    public static int parseStringToInt(String input) {
        try {
            validateContainSpace(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException(ExceptionMessage.IS_INVALID_DATE.getMessage());
        }
    }

    private static void validateContainSpace(String input) {
        if (hasWhiteSpace(input)) {
            throw new IllegalArgumentException(ExceptionMessage.IS_INVALID_DATE.getMessage());
        }
    }

    private static boolean hasWhiteSpace(String input) {
        return input.chars().anyMatch(Character::isWhitespace);
    }
}
