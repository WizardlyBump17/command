package com.wizardlybump17.commands.manager;

import com.wizardlybump17.commands.util.Arguments;
import com.wizardlybump17.commands.factory.CommandFactory;
import com.wizardlybump17.commands.pair.Pair;
import com.wizardlybump17.commands.registered.RegisteredCommand;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.sender.CommandSender;
import lombok.NonNull;

import java.util.*;

public class CommandManager {

    private final @NonNull Set<RegisteredCommand<?>> commands = new TreeSet<>();

    public @NonNull Optional<Pair<RegisteredCommand<?>, CommandExecutionResult>> execute(@NonNull CommandSender<?> sender, @NonNull String command) {
        List<String> arguments = Arguments.getArguments(command);
        if (arguments.isEmpty())
            return Optional.empty();

        for (RegisteredCommand<?> registeredCommand : commands) {
            CommandExecutionResult result = registeredCommand.execute(sender, arguments);
            if (result.stopExecution())
                return Optional.of(Pair.of(registeredCommand, result));
        }

        return Optional.empty();
    }

    public <T, C extends RegisteredCommand<?>> @NonNull List<C> registerCommand(@NonNull CommandFactory<T, C> factory, @NonNull T object) {
        List<C> commands = factory.create(object);
        this.commands.addAll(commands);
        return commands;
    }
}
