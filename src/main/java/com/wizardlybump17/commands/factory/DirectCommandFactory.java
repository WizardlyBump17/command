package com.wizardlybump17.commands.factory;

import com.wizardlybump17.commands.executor.CommandExecutor;
import com.wizardlybump17.commands.registered.DirectRegisteredCommand;
import lombok.NonNull;

import java.util.List;
import java.util.logging.Logger;

/**
 * <p>Factory to create a {@link DirectRegisteredCommand} from a {@link CommandExecutor}.</p>
 */
public class DirectCommandFactory extends CommandFactory<CommandExecutor, DirectRegisteredCommand> {

    public DirectCommandFactory(@NonNull Logger logger) {
        super(logger);
    }

    /**
     * <p>Returns a {@link List} using the {@link List#of(Object)} method with a single {@link DirectRegisteredCommand}.</p>
     * @param object the object to create the command from
     * @return a {@link List} with a single {@link DirectRegisteredCommand}
     */
    @Override
    public @NonNull List<DirectRegisteredCommand> createCommands(@NonNull CommandExecutor object) {
        return List.of(new DirectRegisteredCommand(object));
    }
}
