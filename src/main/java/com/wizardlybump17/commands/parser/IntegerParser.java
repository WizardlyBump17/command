package com.wizardlybump17.commands.parser;

import com.wizardlybump17.commands.sender.CommandSender;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IntegerParser extends ArgumentParser<Integer> {

    public IntegerParser() {
        super(List.of(Integer.class, int.class));
    }

    @Override
    public @Nullable Integer parse(@NonNull String argument) {
        try {
            return Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public boolean isValid(@NonNull String argument) {
        try {
            Integer.parseInt(argument);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public @NonNull List<String> autoComplete(@NonNull CommandSender<?> sender, @NonNull String fullCommand, @NonNull String currentArgument) {
        return List.of("1", "10", "100");
    }
}
