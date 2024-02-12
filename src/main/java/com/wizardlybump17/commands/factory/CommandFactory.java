package com.wizardlybump17.commands.factory;

import com.wizardlybump17.commands.registered.RegisteredCommand;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.logging.Logger;

@Data
public abstract class CommandFactory<T, C extends RegisteredCommand<?>> {

    private final @NonNull Logger logger;

    /**
     * <p>Creates a list of commands from the given object.</p>
     * @param object the object to create the commands from
     * @return the list of commands
     */
    public abstract @NonNull List<C> create(@NonNull T object);
}
