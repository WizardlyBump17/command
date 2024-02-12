package com.wizardlybump17.commands.sender;

import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;

@Data
public class ConsoleSender implements CommandSender<PrintStream> {

    private boolean allPermissions;

    public ConsoleSender(boolean allPermissions) {
        this.allPermissions = allPermissions;
    }

    public ConsoleSender() {
        this(true);
    }

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
        return allPermissions;
    }
}
