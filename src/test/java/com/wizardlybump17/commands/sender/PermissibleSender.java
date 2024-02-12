package com.wizardlybump17.commands.sender;

import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;
import java.util.Set;

@Data
public class PermissibleSender implements CommandSender<PrintStream> {

    private final @NonNull Set<String> permissions;

    @Override
    public @NonNull PrintStream getHandle() {
        return System.out;
    }

    @Override
    public void sendMessage(@Nullable String message) {
        if (message != null)
            getHandle().println(message);
    }

    @Override
    public void sendMessage(@Nullable String @NonNull ... messages) {
        for (String message : messages)
            if (message != null)
                getHandle().println(message);
    }

    @Override
    public boolean hasPermission(@Nullable String permission) {
        return permission == null || permissions.contains(permission);
    }
}
