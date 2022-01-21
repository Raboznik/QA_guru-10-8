package ru.yandex.mkruchkov;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Junit {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException {

        for (Method declaredMethod : SimpleTest.class.getDeclaredMethods()) {
            Test test = declaredMethod.getAnnotation(Test.class);
            if (test != null) {
                try {
                    {
                        declaredMethod.invoke(SimpleTest.class.getConstructor().newInstance());
                    }
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.printf("Test %s failed : %s\n", declaredMethod.getName(), e.getCause().getMessage());
                        continue;
                    } else {
                        System.out.printf("Test %s broken : %s\n", declaredMethod.getName(), e.getCause().getMessage());
                        continue;
                    }
                }
                System.out.printf("Test %s passed!\n", declaredMethod.getName());
            }

        }
    }
}
