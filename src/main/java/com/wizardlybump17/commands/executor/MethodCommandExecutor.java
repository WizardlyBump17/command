package com.wizardlybump17.commands.executor;

import com.wizardlybump17.commands.command.Command;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.sender.CommandSender;
import lombok.NonNull;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public record MethodCommandExecutor(@NonNull Object object, @NonNull MethodHandle methodHandle, @NonNull Method method, @NonNull Command command) implements CommandExecutor {

    @Override
    public @NonNull Command getCommand() {
        return command();
    }

    @Override
    public @NonNull CommandExecutionResult execute(@NonNull CommandSender<?> sender, @NonNull List<Object> arguments) {
        arguments = new ArrayList<>(arguments);
        arguments.add(0, object);
        arguments.add(1, sender);
        try {
            Object returnedValue = methodHandle.invokeWithArguments(arguments);
            if (returnedValue instanceof CommandExecutionResult result)
                return result;
            return CommandExecutionResult.success();
        } catch (Throwable e) {
            return CommandExecutionResult.exceptionally(e);
        }
    }
}
