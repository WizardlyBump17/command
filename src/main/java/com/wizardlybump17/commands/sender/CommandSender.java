package com.wizardlybump17.commands.sender;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public interface CommandSender<T> {

    @NonNull T getHandle();

    void sendMessage(@Nullable String message);

    void sendMessage(@Nullable String @NonNull ... messages);

    boolean hasPermission(@Nullable String permission);
}
