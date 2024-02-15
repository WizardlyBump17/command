package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.command.Command;
import lombok.NonNull;

public record InvalidCommandSenderResult(@NonNull Command command, @NonNull Class<?> expected, @NonNull Class<?> got) implements CommandExecutionResult {

    @Override
    public boolean stopExecution() {
        return true;
    }
}
