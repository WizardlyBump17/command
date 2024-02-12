package com.wizardlybump17.commands.sender;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;

public class ConsoleSender implements CommandSender<PrintStream> {

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
        return true;
    }
}
