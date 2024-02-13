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

/**
 * <p>
 *     A factory to create {@link RegisteredMethodCommand}s from an object.<br>
 *     This factory uses reflection to find methods annotated with {@link Command} and creates a {@link MethodCommandExecutor} for each one.
 * </p>
 * @see #createCommands(Object)
 * @see RegisteredMethodCommand
 * @see MethodCommandExecutor
 */
public class MethodCommandFactory extends CommandFactory<Object, RegisteredMethodCommand> {

    public MethodCommandFactory(@NonNull Logger logger) {
        super(logger);
    }

    /**
     * <p>
     *     Creates a {@link List} of {@link RegisteredMethodCommand}s from the given object.<br>
     *     The process is done by using reflection to find methods annotated with {@link Command} and creating a {@link MethodCommandExecutor} for each one:
     *     <ol>
     *         <li>Iterates over all methods of the object;</li>
     *         <li>Checks if the method has the {@link Command} annotation;</li>
     *         <li>If the method has the annotation, checks if it is public and not static;</li>
     *         <li>Creates a {@link MethodHandle} for the method;</li>
     *         <li>Creates a {@link com.wizardlybump17.commands.command.Command} from the annotation;</li>
     *         <li>Creates a {@link MethodCommandExecutor} from the object, the method handle, the method and the command;</li>
     *         <li>Adds the {@link MethodCommandExecutor} to the {@link List} of commands.</li>
     *     </ol>
     * </p>
     * @param object the object to create the commands from
     * @return a {@link List} of created {@link RegisteredMethodCommand}s
     * @see RegisteredMethodCommand
     * @see MethodCommandExecutor
     */
    @Override
    public @NonNull List<RegisteredMethodCommand> createCommands(@NonNull Object object) {
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
