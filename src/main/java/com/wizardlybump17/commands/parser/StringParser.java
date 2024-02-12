package com.wizardlybump17.commands.parser;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StringParser extends ArgumentParser<String> {

    public StringParser() {
        super(List.of(String.class));
    }

    @Override
    public @Nullable String parse(@NonNull String argument) {
        return argument;
    }

    @Override
    public boolean isValid(@NonNull String argument) {
        return true;
    }
}
