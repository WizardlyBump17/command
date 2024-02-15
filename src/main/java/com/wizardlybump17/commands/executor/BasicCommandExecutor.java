package com.wizardlybump17.commands.executor;

import com.wizardlybump17.commands.command.Command;
import com.wizardlybump17.commands.result.CommandExecutionResult;
import com.wizardlybump17.commands.sender.CommandSender;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.function.Supplier;

/**
 * <p>
 *     Basic implementation of the {@link CommandExecutor} interface.<br>
 *     It is used to execute a {@link Command} without relying on a method.
 * </p>
 */
@Data
public class BasicCommandExecutor implements CommandExecutor {

    private final @NonNull Command command;
    private final @NonNull Supplier<CommandExecutionResult> resultSupplier;

    public BasicCommandExecutor(@NonNull Command command, @NonNull Supplier<CommandExecutionResult> resultSupplier) {
        this.command = command;
        this.resultSupplier = resultSupplier;
    }

    /**
     * <p>Creates a new {@link BasicCommandExecutor} with the given {@link Command} and with the {@link CommandExecutionResult#success(Command)} as the result supplier.</p>
     * @param command the command to be executed
     */
    public BasicCommandExecutor(@NonNull Command command) {
        this(command, () -> CommandExecutionResult.success(command));
    }

    @Override
    public @NonNull CommandExecutionResult execute(@NonNull CommandSender<?> sender, @NonNull List<Object> arguments) {
        return resultSupplier.get();
    }
}
