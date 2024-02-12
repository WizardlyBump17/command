package com.wizardlybump17.commands.factory;

import com.wizardlybump17.commands.annotation.Command;
import com.wizardlybump17.commands.executor.MethodCommandExecutor;
import com.wizardlybump17.commands.registered.RegisteredMethodCommand;
import lombok.NonNull;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MethodCommandFactory extends CommandFactory<Object, RegisteredMethodCommand> {

    public MethodCommandFactory(@NonNull Logger logger) {
        super(logger);
    }

    @Override
    public @NonNull List<RegisteredMethodCommand> create(@NonNull Object object) {
        List<RegisteredMethodCommand> commands = new ArrayList<>();

        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            Command annotation = method.getAnnotation(Command.class);
            if (annotation == null)
                continue;

            if (!Modifier.isPublic(method.getModifiers())) {
                getLogger().warning("The method " + method.getName() + " is not public and cannot be registered as a command.");
                continue;
            }

            if (Modifier.isStatic(method.getModifiers())) {
                getLogger().warning("The method " + method.getName() + "is static and cannot be registered as a command.");
                continue;
            }

            MethodHandle handle;
            try {
                handle = MethodHandles.lookup().findVirtual(clazz, method.getName(), MethodType.methodType(method.getReturnType(), method.getParameterTypes()));
            } catch (NoSuchMethodException | IllegalAccessException e) {
                getLogger().log(Level.SEVERE, "Error while fetching the method " + method.getName(), e);
                continue;
            }

            com.wizardlybump17.commands.command.Command command = new com.wizardlybump17.commands.command.Command(
                    annotation.value(),
                    annotation.priority(),
                    annotation.permission().isEmpty() ? null : annotation.permission()
            );
            MethodCommandExecutor executor = new MethodCommandExecutor(object, handle, method, command);

            commands.add(new RegisteredMethodCommand(executor));
        }

        return commands;
    }
}
