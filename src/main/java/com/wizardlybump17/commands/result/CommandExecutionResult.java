package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.command.Command;
import com.wizardlybump17.commands.parser.ArgumentParser;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public interface CommandExecutionResult {

    @NonNull Command command();

    default boolean stopExecution() {
        return false;
    }

    static @NonNull SuccessfulResult success(@NonNull Command command) {
        return new SuccessfulResult(command);
    }

    static @NonNull ExceptionResult exceptionally(@NonNull Command command, @NonNull Throwable exception) {
        return new ExceptionResult(command, exception);
    }

    static @NonNull MissingPermissionResult missingPermission(@NonNull Command command, @Nullable String permission) {
        return new MissingPermissionResult(command, permission);
    }

    static @NonNull GenericFailureResult failure(@NonNull Command command) {
        return new GenericFailureResult(command);
    }

    static @NonNull WrongArgumentsSizeResult wrongArgumentsSize(@NonNull Command command) {
        return new WrongArgumentsSizeResult(command);
    }

    static @NonNull WrongArgumentsResult wrongArguments(@NonNull Command command) {
        return new WrongArgumentsResult(command);
    }

    static @NonNull InvalidArgumentResult invalidArgument(@NonNull Command command, @NonNull String argument, @NonNull ArgumentParser<?> parser) {
        return new InvalidArgumentResult(command, argument, parser);
    }

    static @NonNull InvalidCommandSenderResult invalidCommandSender(@NonNull Command command, @NonNull Class<?> expected, @NonNull Class<?> got) {
        return new InvalidCommandSenderResult(command, expected, got);
    }
}
