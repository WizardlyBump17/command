package com.wizardlybump17.commands.annotation;

import com.wizardlybump17.commands.registered.RegisteredMethodCommand;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     An annotation to mark a method as a command.<br>
 *     When choosing this system, you must use the {@link com.wizardlybump17.commands.factory.MethodCommandFactory}.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    /**
     * <p>
     *     The command syntax.<br>
     *     The syntax is parsed by the {@link RegisteredMethodCommand#createNodes()} method as the following:
     *     <ul>
     *         <li>The command is split using spaces;</li>
     *         <li>Each part of the command is parsed as an {@link com.wizardlybump17.commands.node.ArgumentNode};</li>
     *         <li>If a part is between arrows (< and >), it is tread as an user input argument.</li>
     *     </ul>
     * </p>
     * @return the command syntax
     */
    String value();

    /**
     * <p>The priority of the command. If set to -1 (default), the priority will be the result of {@code Command#value().split(" ").length}.</p>
     * @return the priority of the command
     */
    int priority() default -1;

    /**
     * <p>The permission required to execute the command. If set to an empty string (default), no permission is required.</p>
     * @return the permission required to execute the command
     */
    String permission() default "";
}
