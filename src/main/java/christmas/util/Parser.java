package christmas.util;

import static christmas.exception.ExceptionMessage.ERROR;
import static christmas.exception.ExceptionMessage.IS_INVALID_DATE;
import static christmas.exception.ExceptionMessage.IS_INVALID_MENU;

import christmas.exception.ChristmasException;
import christmas.exception.ExceptionMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    private static final String DELIMITER = ",";
    private static final String HYPHEN = "-";
    private static final String regex = "[\\w가-힣]+" + HYPHEN + "\\d+";

    private Parser() {
    }

    public static int parseStringToInt(String input) {
        try {
            validateDateContainSpace(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw ChristmasException.invalidDate(new NumberFormatException());
        }
    }

    public static Map<String, Integer> parseStringToMap(String input) {
        try {
            validateMenuContainSpace(input);
            List<String> splitInput = validateEndsWithDelimiter(input);
            validateFormatWithHyphen(splitInput);
            validateDuplicateMenu(splitInput);
            return splitInput.stream()
                    .collect(Collectors.toMap(
                            s -> s.split(HYPHEN)[0],
                            s -> Integer.parseInt(s.split(HYPHEN)[1])
                    ));
        } catch (IllegalArgumentException exception) {
            throw ChristmasException.invalidMenu(new IllegalArgumentException());
        }
    }

    private static void validateDateContainSpace(String input) {
        if (hasWhiteSpace(input)) {
            throw ChristmasException.invalidDate(new IllegalArgumentException());
        }
    }

    private static void validateMenuContainSpace(String input) {
        if (hasWhiteSpace(input)) {
            throw ChristmasException.invalidMenu(new IllegalArgumentException());
        }
    }

    private static List<String> validateEndsWithDelimiter(String input) {
        if (isEndsWithDelimiter(input)) {
            throw ChristmasException.invalidMenu(new IllegalArgumentException());
        }
        return Arrays.stream(input.split(DELIMITER))
                .toList();
    }


    private static void validateFormatWithHyphen(List<String> input) {
        boolean isValidFormat = input.stream()
                .allMatch(Parser::isValidFormat);
        if (!isValidFormat) {
            throw ChristmasException.invalidMenu(new IllegalArgumentException());
        }
    }

    private static void validateDuplicateMenu(List<String> input) {
        Set<String> uniqueMenuNames = new HashSet<>();
        if (input.stream().anyMatch(name -> !uniqueMenuNames.add(name))) {
            throw ChristmasException.invalidMenu(new IllegalArgumentException());
        }
    }

    private static boolean hasWhiteSpace(String input) {
        return input.chars().anyMatch(Character::isWhitespace);
    }

    private static boolean isEndsWithDelimiter(String input) {
        return input.endsWith(DELIMITER);
    }

    private static boolean isValidFormat(String s) {
        return Pattern.matches(regex, s);
    }
}
