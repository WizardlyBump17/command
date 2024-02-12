package com.wizardlybump17.commands.registered;

import com.wizardlybump17.commands.executor.CommandExecutor;
import com.wizardlybump17.commands.node.ArgumentNode;
import com.wizardlybump17.commands.util.Arguments;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>A {@link RegisteredCommand} intended to be used with the direct command system.</p>
 */
public class DirectRegisteredCommand extends RegisteredCommand<CommandExecutor> {

    public DirectRegisteredCommand(@NonNull CommandExecutor executor) {
        super(executor);
    }

    /**
     * <p>
     *     Creates a {@link List} of {@link ArgumentNode} based on the command execution.<br>
     *     The {@link List} is created by splitting the command into spaces and creating a new {@link ArgumentNode} for each part of the command.
     * </p>
     * @return the {@link List} of argument nodes
     */
    @Override
    public @NonNull List<ArgumentNode> createNodes() {
        List<ArgumentNode> nodes = new ArrayList<>();

        String execution = getCommand().execution();
        String[] split = execution.split(" ");

        for (String argument : split)
            nodes.add(new ArgumentNode(null, Arguments.getAliases(argument)));

        return nodes;
    }
}
