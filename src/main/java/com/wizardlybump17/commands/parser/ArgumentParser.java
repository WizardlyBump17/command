package com.wizardlybump17.commands.parser;

import com.wizardlybump17.commands.sender.CommandSender;
import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Data
public abstract class ArgumentParser<T> {

    private final @NonNull List<Class<?>> triggerTypes;

    public abstract @Nullable T parse(@NonNull String argument);

    public abstract boolean isValid(@NonNull String argument);

    public @NonNull List<String> autoComplete(@NonNull CommandSender<?> sender, @NonNull String fullCommand, @NonNull String currentArgument) {
        return Collections.emptyList();
    }
}
