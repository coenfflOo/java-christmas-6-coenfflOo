package christmas.service;

public class Parser {
    private Parser() {
    }

    public static int parseStringToInt(String input) {
        try {
            //입력받은 날짜에 대한 예외 처리
            return Integer.parseInt(input);
        } catch (Exception exception) {
            //예외 던지기
//            throw new
            return 0;
        }
    }
}
