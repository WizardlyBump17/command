package com.wizardlybump17.commands.result;

import org.jetbrains.annotations.Nullable;

public record MissingPermissionResult(@Nullable String permission) implements CommandExecutionResult {

    @Override
    public boolean stopExecution() {
        return true;
    }
}
