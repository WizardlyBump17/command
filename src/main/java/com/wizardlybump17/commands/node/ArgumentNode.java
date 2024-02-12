package com.wizardlybump17.commands.node;

import com.wizardlybump17.commands.parser.ArgumentParser;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record ArgumentNode(@Nullable ArgumentParser<?> parser, @NonNull List<String> aliases) {

    public @Nullable Object parse(@NonNull String argument) {
        return parser == null ? null : parser.parse(argument);
    }

    public boolean isLiteral() {
        return parser == null;
    }
}
