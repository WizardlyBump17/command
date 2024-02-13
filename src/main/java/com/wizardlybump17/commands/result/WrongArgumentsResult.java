package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.command.Command;
import lombok.NonNull;

public record WrongArgumentsResult(@NonNull Command command) implements CommandExecutionResult {
}
