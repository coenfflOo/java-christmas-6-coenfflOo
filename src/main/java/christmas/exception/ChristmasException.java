package christmas.exception;

import static christmas.exception.ExceptionMessage.*;

public class ChristmasException extends IllegalArgumentException {
    private ChristmasException(ExceptionMessage exceptionMessage, Exception exception) {
        super(ERROR.getMessage() + exceptionMessage.getMessage(), exception);
    }

    public static ChristmasException invalidDate(Exception exception) {
        return new ChristmasException(IS_INVALID_DATE, exception);
    }

    public static ChristmasException invalidMenu(Exception exception) {
        return new ChristmasException(IS_INVALID_MENU, exception);
    }

}
