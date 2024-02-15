package com.wizardlybump17.commands.util;

import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;
import java.util.function.Predicate;

@UtilityClass
public class AssertionUtil {

    public static void assertEquals(Optional<?> optional, Object object) {
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), object);
    }

    public static <T> void assertTrue(Optional<T> optional, Predicate<T> condition) {
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertTrue(condition.test(optional.get()));
    }
}
