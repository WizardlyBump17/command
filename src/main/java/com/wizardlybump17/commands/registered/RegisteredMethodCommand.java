package com.wizardlybump17.commands.registered;

import com.wizardlybump17.commands.executor.MethodCommandExecutor;
import com.wizardlybump17.commands.node.ArgumentNode;
import com.wizardlybump17.commands.registry.ArgumentParserRegistry;
import com.wizardlybump17.commands.util.Arguments;
import lombok.NonNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegisteredMethodCommand extends RegisteredCommand<MethodCommandExecutor> {

    public RegisteredMethodCommand(@NonNull MethodCommandExecutor executor) {
        super(executor);
    }

    @Override
    public @NonNull List<ArgumentNode> createNodes() {
        List<ArgumentNode> nodes = new ArrayList<>();

        Method method = getExecutor().method();
        Class<?>[] types = method.getParameterTypes();
        String command = getExecutor().command().execution();
        String[] split = command.split(" ");

        int currentParam = 1;
        for (String argument : split) {
            if (isUserInput(argument))
                nodes.add(new ArgumentNode(ArgumentParserRegistry.INSTANCE.get(types[currentParam++]).orElseThrow(), Collections.emptyList()));
            else
                nodes.add(new ArgumentNode(null, Arguments.getAliases(argument)));
        }

        return nodes;
    }

    public boolean isUserInput(@NonNull String string) {
        return string.charAt(0) == '<' && string.charAt(string.length() - 1) == '>';
    }
}
