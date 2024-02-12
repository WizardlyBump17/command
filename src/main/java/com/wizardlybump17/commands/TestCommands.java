package com.wizardlybump17.commands;

import com.wizardlybump17.commands.annotation.Command;
import com.wizardlybump17.commands.sender.CommandSender;
import lombok.NonNull;

public class TestCommands {

    @Command(value = "test", permission = "test")
    public void test(@NonNull CommandSender<?> sender) {
        sender.sendMessage("Test");
    }

    @Command("test <test>")
    public void test(@NonNull CommandSender<?> sender, String test) {
        sender.sendMessage("Test: \"" + test + "\"");
    }

    @Command("test int <int>")
    public void testInt(@NonNull CommandSender<?> sender, int test) {
        sender.sendMessage("Test int: " + test);
    }

    @Command(value = "test int <int>", priority = 4)
    public void testInt2(@NonNull CommandSender<?> sender, int test) {
        sender.sendMessage("Test int2: " + test);
    }

    @Command("test exception")
    public void testException(@NonNull CommandSender<?> sender) {
        throw new RuntimeException("Test exception");
    }

    @Command("fallback")
    public void fallback(@NonNull CommandSender<?> sender) {
        sender.sendMessage("Fallback");
    }

    @Command("fallback <message> <another>")
    public void fallback(@NonNull CommandSender<?> sender, String message, String another) {
        sender.sendMessage("Fallback: " + message + " " + another);
    }

    @Command("alias test|t")
    public void alias(@NonNull CommandSender<?> sender) {
        sender.sendMessage("Alias");
    }
}
