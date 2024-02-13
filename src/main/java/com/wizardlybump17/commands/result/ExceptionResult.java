package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.command.Command;
import lombok.NonNull;

public record ExceptionResult(@NonNull Command command, @NonNull Throwable exception) implements CommandExecutionResult {

    @Override
    public boolean stopExecution() {
        return true;
    }
}
