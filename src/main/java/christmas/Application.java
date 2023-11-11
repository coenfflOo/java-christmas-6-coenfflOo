package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.ChristmasMainController;

public class Application {
    public static void main(String[] args) {
        ChristmasMainController.start();
        Console.close();
    }
}
