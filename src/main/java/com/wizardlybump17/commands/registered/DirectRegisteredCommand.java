package com.wizardlybump17.commands.registered;

import com.wizardlybump17.commands.executor.CommandExecutor;
import com.wizardlybump17.commands.node.ArgumentNode;
import com.wizardlybump17.commands.util.Arguments;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DirectRegisteredCommand extends RegisteredCommand<CommandExecutor> {

    public DirectRegisteredCommand(@NonNull CommandExecutor executor) {
        super(executor);
    }

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
