package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.command.Command;
import lombok.NonNull;

public record GenericFailureResult(@NonNull Command command) implements CommandExecutionResult {

    @Override
    public boolean stopExecution() {
        return true;
    }
}
