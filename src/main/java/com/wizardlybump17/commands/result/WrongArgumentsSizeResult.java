package com.wizardlybump17.commands.result;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class WrongArgumentsSizeResult implements CommandExecutionResult {

    public static final @NonNull WrongArgumentsSizeResult INSTANCE = new WrongArgumentsSizeResult();
}
