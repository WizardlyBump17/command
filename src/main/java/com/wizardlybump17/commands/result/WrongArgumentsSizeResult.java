package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.command.Command;
import lombok.NonNull;

public record WrongArgumentsSizeResult(@NonNull Command command) implements CommandExecutionResult {
}
