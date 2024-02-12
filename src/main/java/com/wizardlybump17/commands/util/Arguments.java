package com.wizardlybump17.commands.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class Arguments {

    public static @NonNull List<String> getArguments(@NonNull String command) {
        String[] split = command.split(" ");
        int splitLength = split.length;
        List<String> arguments = new ArrayList<>(splitLength);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < splitLength; i++) {
            String string = split[i];
            int length = string.length();
            if (length == 0)
                continue;

            if (start(string, i, splitLength, stringBuilder, arguments) || end(string, stringBuilder, arguments))
                continue;

            if (stringBuilder.isEmpty()) {
                arguments.add(string);
                continue;
            }

            stringBuilder.append(string).append(' ');
        }

        if (!stringBuilder.isEmpty())
            return Collections.emptyList();

        return arguments;
    }

    public static boolean start(@NonNull String string, int i, int splitLength, @NonNull StringBuilder stringBuilder, @NonNull List<String> arguments) {
        int length = string.length();
        if (string.charAt(0) != '"')
            return false;

        //possible start
        if (length == 1) { //only a quote
            if (i + 1 < splitLength) { //there are more strings
                if (stringBuilder.isEmpty()) {
                    stringBuilder.append('"');
                    return true;
                }

                arguments.add(stringBuilder.substring(1).trim());
                stringBuilder.setLength(0);
                return true;
            }

            //no more strings
            if (stringBuilder.isEmpty()) {
                arguments.add("\""); //only a quote
                return true;
            }

            arguments.add(stringBuilder.substring(1).trim()); //end of the string
            stringBuilder.setLength(0);
            return true;
        }

        if (string.charAt(length - 1) == '"') {
            if (length >= 3 && string.charAt(length - 2) == '\\') { //escaped quote
                stringBuilder.append(string, 0, length - 2).append(' ');
                return true;
            }

            //end of the string
            stringBuilder.append(string);
            arguments.add(stringBuilder.substring(1, stringBuilder.length() - 1));
            stringBuilder.setLength(0);
            return true;
        }

        //started the string
        stringBuilder.append(string).append(' ');
        return true;
    }

    public static boolean end(@NonNull String string, @NonNull StringBuilder stringBuilder, @NonNull List<String> arguments) {
        int length = string.length();

        if (string.charAt(length - 1) != '"')
            return false;

        //possible end
        if (length >= 2 && string.charAt(length - 2) == '\\') { //escaped quote
            if (stringBuilder.isEmpty()) {
                arguments.add(string.substring(0, length - 2) + '"');
                return true;
            }

            stringBuilder.append(string, 0, length - 2).append("\" ");
            return true;
        }

        //end of the string
        if (stringBuilder.isEmpty()) {
            arguments.add(string);
            return true;
        }

        arguments.add(stringBuilder.append(string).substring(1, stringBuilder.length() - 1).trim());
        stringBuilder.setLength(0);
        return true;
    }

    public static @NonNull List<String> getAliases(@NonNull String argument) {
        List<String> aliases = new ArrayList<>();
        for (String string : argument.split("\\|"))
            if (!string.isEmpty())
                aliases.add(string.toLowerCase());
        return aliases;
    }
}
