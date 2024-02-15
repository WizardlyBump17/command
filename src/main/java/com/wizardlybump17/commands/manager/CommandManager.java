package com.wizardlybump17.commands.manager;

import com.wizardlybump17.commands.factory.CommandFactory;
import com.wizardlybump17.commands.registered.RegisteredCommand;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.sender.CommandSender;
import com.wizardlybump17.commands.util.Arguments;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

@Data
public class CommandManager {

    private final @NonNull Set<RegisteredCommand<?>> commands = new TreeSet<>();
    private final @NonNull Logger logger;

    /**
     * <p>
     *     Parses the given command into a {@link List} of arguments and iterates over all registered commands until one
     *     of the {@link RegisteredCommand#execute(CommandSender, List)} returns a {@link CommandExecutionResult#stopExecution()} that returns true.
     * </p>
     * @param sender who is executing the command
     * @param command the command to be executed
     * @return an {@link Optional} containing the result of the command execution, if any
     */
    public @NonNull Optional<CommandExecutionResult> execute(@NonNull CommandSender<?> sender, @NonNull String command) {
        List<String> arguments = Arguments.getArguments(command);
        if (arguments.isEmpty())
            return Optional.empty();

        for (RegisteredCommand<?> registeredCommand : commands) {
            CommandExecutionResult result = registeredCommand.execute(sender, arguments);
            if (result.stopExecution())
                return Optional.of(result);
        }

        return Optional.empty();
    }

    public <T, C extends RegisteredCommand<?>> @NonNull List<C> registerCommands(@NonNull CommandFactory<T, C> factory, @NonNull T object) {
        List<C> commands = factory.createCommands(object);
        this.commands.addAll(commands);
        return commands;
    }
}
