package com.wizardlybump17.commands.command;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p>A command.</p>
 * @param execution the command execution
 * @param priority the priority of the command. If set to -1, the priority will be the result of {@code Command#execution().split(" ").length}
 * @param permission the permission required to execute the command
 */
public record Command(@NonNull String execution, int priority, @Nullable String permission) {

    public Command(@NonNull String execution, int priority, @Nullable String permission) {
        this.execution = execution;
        this.priority = priority == -1 ? execution.split(" ").length : priority;
        this.permission = permission;
    }
}
