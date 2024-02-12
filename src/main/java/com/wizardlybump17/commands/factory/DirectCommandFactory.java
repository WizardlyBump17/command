package com.wizardlybump17.commands.factory;

import com.wizardlybump17.commands.executor.CommandExecutor;
import com.wizardlybump17.commands.registered.DirectRegisteredCommand;
import lombok.NonNull;

import java.util.List;
import java.util.logging.Logger;

public class DirectCommandFactory extends CommandFactory<CommandExecutor, DirectRegisteredCommand> {

    public DirectCommandFactory(@NonNull Logger logger) {
        super(logger);
    }

    @Override
    public @NonNull List<DirectRegisteredCommand> create(@NonNull CommandExecutor object) {
        return List.of(new DirectRegisteredCommand(object));
    }
}
