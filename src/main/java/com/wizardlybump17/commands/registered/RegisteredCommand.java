package com.wizardlybump17.commands.registered;

import com.wizardlybump17.commands.command.Command;
import com.wizardlybump17.commands.executor.CommandExecutor;
import com.wizardlybump17.commands.node.ArgumentNode;
import com.wizardlybump17.commands.parser.ArgumentParser;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.sender.CommandSender;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class RegisteredCommand<E extends CommandExecutor> implements Comparable<RegisteredCommand<?>> {

    private final @NonNull E executor;
    private final @NonNull List<ArgumentNode> nodes;

    public RegisteredCommand(@NonNull E executor) {
        this.executor = executor;
        this.nodes = createNodes();
    }

    public abstract @NonNull List<ArgumentNode> createNodes();

    public @NonNull Command getCommand() {
        return executor.getCommand();
    }

    public int getPriority() {
        return getCommand().priority();
    }

    /**
     * <p>Executes the command with the given arguments.</p>
     * <p>Generally called by the {@link com.wizardlybump17.commands.manager.CommandManager#execute(CommandSender, String)} method.</p>
     * @param sender who is executing the command
     * @param arguments the command arguments
     * @return if the command was executed
     */
    public @NonNull CommandExecutionResult execute(@NonNull CommandSender<?> sender, @NonNull List<String> arguments) {
        if (nodes.size() > arguments.size())
            return CommandExecutionResult.wrongArgumentsSize(getCommand());

        List<Object> parsedArguments = new ArrayList<>(nodes.size());

        for (int i = 0; i < arguments.size() && i < nodes.size(); i++) {
            ArgumentNode node = nodes.get(i);
            String argument = arguments.get(i);
            ArgumentParser<?> parser = node.parser();

            if (parser == null) {
                if (node.aliases().contains(argument.toLowerCase()))
                    continue;
                return CommandExecutionResult.wrongArguments(getCommand());
            }

            if (!parser.isValid(argument))
                return CommandExecutionResult.invalidArgument(getCommand(), argument, parser);

            parsedArguments.add(parser.parse(argument));
        }

        if (!executor.canExecute(sender))
            return CommandExecutionResult.missingPermission(getCommand(), getCommand().permission());

        return executor.execute(sender, parsedArguments);
    }

    @Override
    public int compareTo(@NonNull RegisteredCommand<?> other) {
        return other.getPriority() > getPriority() ? 1 : -1;
    }
}
