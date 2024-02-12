package com.wizardlybump17.commands.result;

import com.wizardlybump17.commands.parser.ArgumentParser;
import lombok.NonNull;

public record InvalidArgumentResult(@NonNull String argument, @NonNull ArgumentParser<?> parser) implements CommandExecutionResult {

    @Override
    public boolean stopExecution() {
        return true;
    }
}
