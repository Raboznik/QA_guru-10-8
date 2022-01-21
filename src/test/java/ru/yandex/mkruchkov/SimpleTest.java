package ru.yandex.mkruchkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @Test
    void test() {
        Assertions.assertTrue(3 < 2);
    }

    @Test
    void test1() {
        Assertions.assertTrue(3 > 2);
    }

    @Test
    void test2() {
        throw new NullPointerException();
    }
}
