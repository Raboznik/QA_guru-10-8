package ru.yandex.mkruchkov;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MetroYandexText {

    @BeforeEach
    @DisplayName("Open yandex.ru/metro/moscow")
    void openPage() {
        open("https://yandex.ru/metro/moscow");
        $(".scheme-objects-view__scheme-svg").shouldBe(visible);
    }


    @AfterEach
    void afterEach() {
        closeWebDriver();
    }


    @Test
    @Disabled
    void disabledTest() {
        System.out.println("Just skipped");
    }


    @CsvSource(value = {
            "Говорово, 3",
            "Крылатское, 3",
            "Савёловская, 1"
    })
    @ParameterizedTest(name = "Csv tests for the number of routes from : {0}")
    void metroYandexTestWithCsv(String testData, int sizeValue) {
        $("._type_from").$(".input__control").setValue(testData).pressEnter();
        $("._type_to").$(".input__control").setValue("Менделеевская").pressEnter();
        $(".metro-sidebar-view__route-snippets").$$(".route-snippet-view").shouldHave(size(sizeValue));
    }


    @ValueSource(strings = {"Коньково", "Беляево"})
    @ParameterizedTest(name = "ValueSource test for the number of routes from : {0}")
    void metroYandexTestWithValueSource(String testData) {
        $("._type_from").$(".input__control").setValue(testData).pressEnter();
        $("._type_to").$(".input__control").setValue("Менделеевская").pressEnter();
        $(".metro-sidebar-view__route-snippets").$$(".route-snippet-view").shouldHave(size(2));
    }


    static Stream<Arguments> argumentsForSearch() {
        return Stream.of(
                Arguments.of("Аникеевка", "Коломенская", 3),
                Arguments.of("Войковская", "Домодедовская", 1)
        );
    }


    @MethodSource("argumentsForSearch")
    @ParameterizedTest(name = "MethodSource test for the number of routes from : {0}")
    void metroYandexTestWithMethodSource(String from, String to, int routesCount) {
        $("._type_from").$(".input__control").setValue(from).pressEnter();
        $("._type_to").$(".input__control").setValue(to).pressEnter();
        $(".metro-sidebar-view__route-snippets").$$(".route-snippet-view").shouldHave(size(routesCount));
    }
}




