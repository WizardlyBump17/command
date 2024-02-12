package com.wizardlybump17.commands.result;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class WrongArgumentsResult implements CommandExecutionResult {

    public static final @NonNull WrongArgumentsResult INSTANCE = new WrongArgumentsResult();
}
