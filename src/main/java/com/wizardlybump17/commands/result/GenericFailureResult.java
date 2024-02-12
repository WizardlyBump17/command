package com.wizardlybump17.commands.result;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class GenericFailureResult implements CommandExecutionResult {

    public static final @NonNull GenericFailureResult INSTANCE = new GenericFailureResult();

    @Override
    public boolean stopExecution() {
        return true;
    }
}
