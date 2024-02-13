package com.wizardlybump17.commands;

import com.wizardlybump17.commands.command.Command;
import com.wizardlybump17.commands.executor.BasicCommandExecutor;
import com.wizardlybump17.commands.executor.CommandExecutor;
import com.wizardlybump17.commands.factory.DirectCommandFactory;
import com.wizardlybump17.commands.factory.MethodCommandFactory;
import com.wizardlybump17.commands.manager.CommandManager;
import com.wizardlybump17.commands.parser.IntegerParser;
import com.wizardlybump17.commands.parser.StringParser;
import com.wizardlybump17.commands.registry.ArgumentParserRegistry;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.result.ExceptionResult;
import com.wizardlybump17.commands.sender.CommandSender;
import com.wizardlybump17.commands.sender.ConsoleSender;
import lombok.NonNull;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("CommandFactory");

        ArgumentParserRegistry.INSTANCE.add(new IntegerParser());
        ArgumentParserRegistry.INSTANCE.add(new StringParser());

        CommandManager manager = new CommandManager();
        manager.registerCommands(new MethodCommandFactory(logger), new TestCommands());

        DirectCommandFactory directCommandFactory = new DirectCommandFactory(logger);
        manager.registerCommands(directCommandFactory, new CommandExecutor() {

            private final @NonNull Command command = new Command("direct", 1, null);

            @Override
            public @NonNull Command getCommand() {
                return command;
            }

            @Override
            public @NonNull CommandExecutionResult execute(@NonNull CommandSender<?> sender, @NonNull List<Object> arguments) {
                System.out.println("Direct command");
                return CommandExecutionResult.success(command);
            }
        });
        manager.registerCommands(directCommandFactory, new BasicCommandExecutor(new Command("direct2", 1, null)));

        Command direct3 = new Command("direct3", 1, null);
        manager.registerCommands(directCommandFactory, new BasicCommandExecutor(direct3, () -> CommandExecutionResult.exceptionally(direct3, new RuntimeException("Direct command with exception"))));

        manager.registerCommands(directCommandFactory, new BasicCommandExecutor(new Command("direct3 test", 1, null)));

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(">> ");

            ConsoleSender sender = new ConsoleSender();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                manager.execute(sender, line)
                        .map(result -> {
                            System.out.println(result);
                            if (result instanceof ExceptionResult exceptionResult)
                                Logger.getLogger("Main").log(Level.SEVERE, "Error while executing the command " + result.command(), exceptionResult.exception());
                            return result;
                        })
                        .orElseGet(() -> {
                            System.out.println("Failed");
                            return null;
                        });

                System.out.print(">> ");
            }
        }
    }
}
