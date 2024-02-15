package com.wizardlybump17.commands.command;

import com.wizardlybump17.commands.sender.CommandSender;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p>A command.</p>
 * @param execution the command execution
 * @param priority the priority of the command. If set to -1, the priority will be the result of {@code Command#execution().split(" ").length}
 * @param permission the permission required to execute the command
 * @param senderType the sender type that can execute the command
 */
public record Command(@NonNull String execution, int priority, @Nullable String permission, @NonNull Class<?> senderType) {

    public Command(@NonNull String execution, int priority, @Nullable String permission, @NonNull Class<?> senderType) {
        this.execution = execution;
        this.priority = priority == -1 ? execution.split(" ").length : priority;
        this.permission = permission;

        if (!CommandSender.class.isAssignableFrom(senderType))
            throw new IllegalArgumentException("The sender type must be a subclass of CommandSender or CommandSender itself.");

        this.senderType = senderType;
    }

    public boolean isValidSender(@NonNull CommandSender<?> sender) {
        return senderType.isAssignableFrom(sender.getClass());
    }
}
