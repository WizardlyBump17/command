package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.command.Command;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public record MissingPermissionResult(@NonNull Command command, @Nullable String permission) implements CommandExecutionResult {

    @Override
    public boolean stopExecution() {
        return true;
    }
}
