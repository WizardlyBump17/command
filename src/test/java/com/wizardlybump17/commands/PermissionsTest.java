package com.wizardlybump17.commands;

import com.wizardlybump17.commands.annotation.Command;
import com.wizardlybump17.commands.factory.MethodCommandFactory;
import com.wizardlybump17.commands.manager.CommandManager;
import com.wizardlybump17.commands.pair.Pair;
import com.wizardlybump17.commands.parser.IntegerParser;
import com.wizardlybump17.commands.parser.StringParser;
import com.wizardlybump17.commands.registered.RegisteredCommand;
import com.wizardlybump17.commands.registry.ArgumentParserRegistry;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.result.MissingPermissionResult;
import com.wizardlybump17.commands.sender.CommandSender;
import com.wizardlybump17.commands.sender.PermissibleSender;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class PermissionsTest {

    private static final @NonNull CommandManager COMMAND_MANAGER = new CommandManager();

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
        COMMAND_MANAGER.registerCommands(new MethodCommandFactory(Logger.getLogger("CommandFactory")), new PermissionsTest());
    }

    @Test
    void testNoPermission() {
        CommandSender<?> sender = new PermissibleSender(Set.of("test.success"));
        Optional<Pair<RegisteredCommand<?>, CommandExecutionResult>> optional = COMMAND_MANAGER.execute(sender, "test no-permission");

        assertTrue(optional.isPresent());

        CommandExecutionResult result = optional.get().second();
        assertTrue(result instanceof MissingPermissionResult);
        assertEquals("test.permission", ((MissingPermissionResult) result).permission());
    }

    @Test
    void testNoPermission2() {
        CommandSender<?> sender = new PermissibleSender(Set.of("test.success"));
        Optional<Pair<RegisteredCommand<?>, CommandExecutionResult>> optional = COMMAND_MANAGER.execute(sender, "test no-permission2");

        assertTrue(optional.isPresent());

        CommandExecutionResult result = optional.get().second();
        assertTrue(result instanceof MissingPermissionResult);
        assertEquals("test.permission2", ((MissingPermissionResult) result).permission());
    }

    @Test
    void testSuccess() {
        CommandSender<?> sender = new PermissibleSender(Set.of("test.success"));
        Optional<Pair<RegisteredCommand<?>, CommandExecutionResult>> optional = COMMAND_MANAGER.execute(sender, "test success");
        assertTrue(optional.isPresent());
        assertSame(optional.get().second(), CommandExecutionResult.success());
    }

    @Command(value = "test no-permission", permission = "test.permission")
    public void noPermission(@NonNull CommandSender<?> sender) {
        sender.sendMessage("Should not happen");
    }

    @Command(value = "test no-permission2", permission = "test.permission2")
    public void noPermission2(@NonNull CommandSender<?> sender) {
        sender.sendMessage("Should not happen");
    }

    @Command(value = "test success", permission = "test.success")
    public void success(@NonNull CommandSender<?> sender) {
        sender.sendMessage("Success");
    }
}