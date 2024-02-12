package com.wizardlybump17.commands;

import com.wizardlybump17.commands.factory.MethodCommandFactory;
import com.wizardlybump17.commands.manager.CommandManager;
import com.wizardlybump17.commands.parser.IntegerParser;
import com.wizardlybump17.commands.parser.StringParser;
import com.wizardlybump17.commands.registry.ArgumentParserRegistry;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.result.ExceptionResult;
import com.wizardlybump17.commands.sender.ConsoleSender;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        ArgumentParserRegistry.INSTANCE.add(new IntegerParser());
        ArgumentParserRegistry.INSTANCE.add(new StringParser());

        CommandManager manager = new CommandManager();
        manager.registerCommand(new MethodCommandFactory(Logger.getLogger("CommandFactory")), new TestCommands());

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(">> ");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                manager.execute(new ConsoleSender(), line)
                        .map(pair -> {
                            CommandExecutionResult result = pair.second();
                            System.out.println(result);
                            if (result instanceof ExceptionResult exceptionResult)
                                Logger.getLogger("Main").log(Level.SEVERE, "Error while executing the command " + pair.first().getCommand(), exceptionResult.exception());
                            return pair;
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
