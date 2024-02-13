package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.command.Command;
import lombok.NonNull;

public record SuccessfulResult(@NonNull Command command) implements CommandExecutionResult {

    @Override
    public boolean stopExecution() {
        return true;
    }
}
