package christmas.domain;

import christmas.exception.ChristmasException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {

    @ParameterizedTest
    @CsvSource({"양송이수프, 2, 12000", "티본스테이크, 3, 165000", "초코케이크, 1, 15000"})
    @DisplayName("정상적인 주문 가격 계산")
    void calculateOriginalPrice_ValidOrder_CorrectTotalPrice(
            String itemName, int quantity, int expectedTotalPrice) {

        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put(itemName, quantity);

        Menu menu = Menu.from(orderMenu);

        assertThat(menu.getOriginalPrice()).isEqualTo(expectedTotalPrice);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pasta", "Coke"})
    @DisplayName("유효하지 않은 메뉴 주문 시 예외 발생")
    void createMenu_InvalidMenu_ThrowsException(String invalidMenuName) {
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put(invalidMenuName, 1);

        assertThatThrownBy(() -> Menu.from(orderMenu))
                .isInstanceOf(ChristmasException.class);
    }

    @ParameterizedTest
    @CsvSource({"시저샐러드, 0", "바비큐립, -1", "아이스크림, -5"})
    @DisplayName("유효하지 않은 수량 주문 시 예외 발생")
    void createMenu_InvalidQuantity_ThrowsException(String itemName, int invalidQuantity) {
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put(itemName, invalidQuantity);

        assertThatThrownBy(() -> Menu.from(orderMenu))
                .isInstanceOf(ChristmasException.class);
    }

    @ParameterizedTest
    @CsvSource({"제로콜라, 2, 레드와인, 1", "레드와인, 3, 샴페인, 1"})
    @DisplayName("음료 카테고리만 주문 시 예외 발생")
    void createMenu_NonDrinkOrder_ThrowsException(String item1, int qty1, String item2, int qty2) {
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put(item1, qty1);
        orderMenu.put(item2, qty2);

        assertThatThrownBy(() -> Menu.from(orderMenu))
                .isInstanceOf(ChristmasException.class);
    }

    @ParameterizedTest
    @CsvSource({"바비큐립, 5, 초코케이크, 5, 제로콜라, 5, 크리스마스파스타, 6",
            "티본스테이크, 5, 해산물파스타, 5, 초코케이크, 7, 레드와인, 30"})
    @DisplayName("주문 메뉴 수량 합이 최대치를 초과할 때 예외 발생")
    void createMenu_ExceedMaxMenuCount_ThrowsException(
            String item1, int qty1, String item2, int qty2,
            String item3, int qty3, String item4, int qty4) {

        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put(item1, qty1);
        orderMenu.put(item2, qty2);
        orderMenu.put(item3, qty3);
        orderMenu.put(item4, qty4);

        assertThatThrownBy(() -> Menu.from(orderMenu))
                .isInstanceOf(ChristmasException.class);
    }

    @ParameterizedTest
    @MethodSource("provideValidOrderMenus")
    @DisplayName("주문 메뉴의 총 가격 계산")
    void calculateTotalPrice(Map<String, Integer> orderMenu, int expectedTotalPrice) {
        Menu menu = Menu.from(orderMenu);

        assertThat(menu.getOriginalPrice()).isEqualTo(expectedTotalPrice);
    }

    private static Stream<Object[]> provideValidOrderMenus() {
        return Stream.of(
                new Object[]{Map.of("티본스테이크", 2, "아이스크림", 1), 115_000},
                new Object[]{Map.of("해산물파스타", 1, "샴페인", 3), 110_000},
                new Object[]{Map.of("양송이수프", 2, "초코케이크", 1, "제로콜라", 3), 36_000}
        );
    }
}
