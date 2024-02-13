package com.wizardlybump17.commands;

import com.wizardlybump17.commands.util.Arguments;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArgumentsTest {

    @Test
    void testArgumentsUntil4th() {
        assertEquals(Arguments.getArguments("test"), List.of("test"));
        assertEquals(Arguments.getArguments("test arg1"), List.of("test", "arg1"));
        assertEquals(Arguments.getArguments("test arg1 arg2"), List.of("test", "arg1", "arg2"));
        assertEquals(Arguments.getArguments("test arg1 arg2 arg3"), List.of("test", "arg1", "arg2", "arg3"));
    }

    @Test
    void testQuotes() {
        assertEquals(Arguments.getArguments("test \"arg1\""), List.of("test", "arg1"));
        assertEquals(Arguments.getArguments("test \"arg1 arg1\""), List.of("test", "arg1 arg1"));
        assertEquals(Arguments.getArguments("test \"arg1 arg1 arg1\""), List.of("test", "arg1 arg1 arg1"));
        assertEquals(Arguments.getArguments("test \"arg1 arg1 arg1\" arg2"), List.of("test", "arg1 arg1 arg1", "arg2"));
        assertEquals(Arguments.getArguments("test \"arg1 arg1 arg1\" arg2 arg3"), List.of("test", "arg1 arg1 arg1", "arg2", "arg3"));
    }

    @Test
    void testSpacesInsideQuotes() { //in a future version, the spaces inside quotes will be preserved
        assertEquals(Arguments.getArguments("test \"arg1 arg1\""), List.of("test", "arg1 arg1"));
        assertEquals(Arguments.getArguments("test \"arg1    arg1\""), List.of("test", "arg1 arg1"));
        assertEquals(Arguments.getArguments("test \"arg1    arg1\" arg2"), List.of("test", "arg1 arg1", "arg2"));
        assertEquals(Arguments.getArguments("test \"   arg1    arg1   \" arg2"), List.of("test", "arg1 arg1", "arg2"));
        assertEquals(Arguments.getArguments("test \"   arg1   \" arg2"), List.of("test", "arg1", "arg2"));
        assertEquals(Arguments.getArguments("test \"   arg1\""), List.of("test", "arg1"));
        assertEquals(Arguments.getArguments("test \"arg1\"   "), List.of("test", "arg1"));
        assertEquals(Arguments.getArguments("test \"    \""), List.of("test", ""));
    }
}
