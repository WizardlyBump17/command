package com.wizardlybump17.commands;

import com.wizardlybump17.commands.annotation.Command;
import com.wizardlybump17.commands.factory.MethodCommandFactory;
import com.wizardlybump17.commands.manager.CommandManager;
import com.wizardlybump17.commands.parser.IntegerParser;
import com.wizardlybump17.commands.parser.StringParser;
import com.wizardlybump17.commands.registry.ArgumentParserRegistry;
import com.wizardlybump17.commands.result.InvalidCommandSenderResult;
import com.wizardlybump17.commands.result.SuccessfulResult;
import com.wizardlybump17.commands.sender.CommandSender;
import com.wizardlybump17.commands.sender.ConsoleSender;
import com.wizardlybump17.commands.sender.PermissibleSender;
import com.wizardlybump17.commands.util.AssertionUtil;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.logging.Logger;

public class SenderTest {

    private static final @NonNull CommandManager COMMAND_MANAGER = new CommandManager(Logger.getLogger("CommandManager"));

    @BeforeAll
    public static void setup() {
        setupArgumentParsers();
        registerCommands();
    }

    public static void setupArgumentParsers() {
        ArgumentParserRegistry.INSTANCE.add(new IntegerParser());
        ArgumentParserRegistry.INSTANCE.add(new StringParser());
    }

    public static void registerCommands() {
        COMMAND_MANAGER.registerCommands(new MethodCommandFactory(Logger.getLogger("CommandFactory")), new SenderTest());
    }

    @Test
    void testSenders() {
        ConsoleSender consoleSender = new ConsoleSender();
        PermissibleSender permissibleSender = new PermissibleSender(new HashSet<>());

        AssertionUtil.assertTrue(COMMAND_MANAGER.execute(consoleSender, "test"), result -> result instanceof SuccessfulResult);
        AssertionUtil.assertTrue(COMMAND_MANAGER.execute(permissibleSender, "test"), result -> result instanceof SuccessfulResult);

        AssertionUtil.assertTrue(COMMAND_MANAGER.execute(consoleSender, "console"), result -> result instanceof SuccessfulResult);
        AssertionUtil.assertTrue(COMMAND_MANAGER.execute(permissibleSender, "console"), result -> result instanceof InvalidCommandSenderResult);

        AssertionUtil.assertTrue(COMMAND_MANAGER.execute(consoleSender, "permissible"), result -> result instanceof InvalidCommandSenderResult);
        AssertionUtil.assertTrue(COMMAND_MANAGER.execute(permissibleSender, "permissible"), result -> result instanceof SuccessfulResult);
    }

    @Command("test")
    public void test(@NonNull CommandSender<?> sender) {
        sender.sendMessage("Success");
    }

    @Command("console")
    public void console(@NonNull ConsoleSender sender) {
        sender.sendMessage("Console");
    }

    @Command("permissible")
    public void permissible(@NonNull PermissibleSender sender) {
        sender.sendMessage("Permissible");
    }
}
