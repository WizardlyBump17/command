package com.wizardlybump17.commands.registry;

import com.wizardlybump17.commands.parser.ArgumentParser;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArgumentParserRegistry {

    public static final @NonNull ArgumentParserRegistry INSTANCE = new ArgumentParserRegistry();

    private final @NonNull Map<Class<?>, ArgumentParser<?>> parsers = new HashMap<>();

    public void add(@NonNull ArgumentParser<?> parser) {
        for (Class<?> triggerType : parser.getTriggerTypes())
            parsers.put(triggerType, parser);
    }

    public void add(@NonNull Class<?> triggerType, @NonNull ArgumentParser<?> parser) {
        parsers.put(triggerType, parser);
    }

    public @NonNull Optional<ArgumentParser<?>> get(@NonNull Class<?> triggerType) {
        return Optional.ofNullable(parsers.get(triggerType));
    }

    public boolean has(@NonNull Class<?> triggerType) {
        return parsers.containsKey(triggerType);
    }

    public void remove(@NonNull Class<?> triggerType) {
        parsers.remove(triggerType);
    }
}
