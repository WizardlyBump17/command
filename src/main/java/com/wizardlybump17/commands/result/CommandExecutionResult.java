package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.parser.ArgumentParser;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public interface CommandExecutionResult {

    default boolean stopExecution() {
        return false;
    }

    static @NonNull SuccessfulResult success() {
        return SuccessfulResult.INSTANCE;
    }

    static @NonNull ExceptionResult exceptionally(@NonNull Throwable exception) {
        return new ExceptionResult(exception);
    }

    static @NonNull MissingPermissionResult missingPermission(@Nullable String permission) {
        return new MissingPermissionResult(permission);
    }

    static @NonNull GenericFailureResult failure() {
        return GenericFailureResult.INSTANCE;
    }

    static @NonNull WrongArgumentsSizeResult wrongArgumentsSize() {
        return WrongArgumentsSizeResult.INSTANCE;
    }

    static @NonNull WrongArgumentsResult wrongArguments() {
        return WrongArgumentsResult.INSTANCE;
    }

    static @NonNull InvalidArgumentResult invalidArgument(@NonNull String argument, @NonNull ArgumentParser<?> parser) {
        return new InvalidArgumentResult(argument, parser);
    }
}
