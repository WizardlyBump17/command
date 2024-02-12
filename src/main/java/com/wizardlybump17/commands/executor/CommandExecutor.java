package com.wizardlybump17.commands.executor;

import com.wizardlybump17.commands.command.Command;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.sender.CommandSender;
import lombok.NonNull;

import java.util.List;

public interface CommandExecutor {

    @NonNull Command getCommand();

    @NonNull CommandExecutionResult execute(@NonNull CommandSender<?> sender, @NonNull List<Object> arguments);

    /**
     * <p>
     *     Checks if the {@link CommandSender} can execute the {@link #getCommand()}.<br>
     *     The default implementation checks if the sender has the permission of the command.
     * </p>
     * @param sender who is executing the command
     * @return if the {@link CommandSender} can execute the command
     */
    default boolean canExecute(@NonNull CommandSender<?> sender) {
        return sender.hasPermission(getCommand().permission());
    }
}
