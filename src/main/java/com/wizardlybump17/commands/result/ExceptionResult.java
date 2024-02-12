package com.wizardlybump17.commands.result;

import lombok.NonNull;

public record ExceptionResult(@NonNull Throwable exception) implements CommandExecutionResult {

    @Override
    public boolean stopExecution() {
        return true;
    }
}
