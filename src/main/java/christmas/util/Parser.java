package christmas.util;

import static christmas.view.constant.ExceptionMessage.ERROR;
import static christmas.view.constant.ExceptionMessage.IS_INVALID_MENU;

import christmas.view.constant.ExceptionMessage;
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
            validateContainSpace(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException(ExceptionMessage.IS_INVALID_DATE.getMessage());
        }
    }

    public static Map<String, Integer> parseStringToMap(String input) {
        try {
            validateContainSpace(input);
            List<String> splitInput = validateEndsWithDelimiter(input);
            validateFormatWithHyphen(splitInput);
            validateDuplicateMenu(splitInput);
            return splitInput.stream()
                    .collect(Collectors.toMap(
                            s -> s.split(HYPHEN)[0],
                            s -> Integer.parseInt(s.split(HYPHEN)[1])
                    ));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(IS_INVALID_MENU.getMessage());
        }
    }

    private static void validateContainSpace(String input) {
        if (hasWhiteSpace(input)) {
            throw new IllegalArgumentException(ERROR.getMessage());
        }
    }

    private static List<String> validateEndsWithDelimiter(String input) {
        if (isEndsWithDelimiter(input)) {
            throw new IllegalArgumentException(IS_INVALID_MENU.getMessage());
        }
        return Arrays.stream(input.split(DELIMITER))
                .toList();
    }


    private static void validateFormatWithHyphen(List<String> input) {
        boolean isValidFormat = input.stream()
                .allMatch(Parser::isValidFormat);
        if (!isValidFormat) {
            throw new IllegalArgumentException(IS_INVALID_MENU.getMessage());
        }
    }

    private static void validateDuplicateMenu(List<String> input) {
        Set<String> uniqueMenuNames = new HashSet<>();
        if (input.stream().anyMatch(name -> !uniqueMenuNames.add(name))) {
            throw new IllegalArgumentException(IS_INVALID_MENU.getMessage()); // 중복된 메뉴
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
