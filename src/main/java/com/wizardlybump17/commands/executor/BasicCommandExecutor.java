package com.wizardlybump17.commands.executor;

import com.wizardlybump17.commands.command.Command;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.sender.CommandSender;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.function.Supplier;

@Data
public class BasicCommandExecutor implements CommandExecutor {

    private final @NonNull Command command;
    private final @NonNull Supplier<CommandExecutionResult> resultSupplier;

    public BasicCommandExecutor(@NonNull Command command, @NonNull Supplier<CommandExecutionResult> resultSupplier) {
        this.command = command;
        this.resultSupplier = resultSupplier;
    }

    public BasicCommandExecutor(@NonNull Command command) {
        this(command, CommandExecutionResult::success);
    }

    @Override
    public @NonNull CommandExecutionResult execute(@NonNull CommandSender<?> sender, @NonNull List<Object> arguments) {
        return resultSupplier.get();
    }
}
