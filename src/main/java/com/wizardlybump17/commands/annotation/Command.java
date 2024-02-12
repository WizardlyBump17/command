package com.wizardlybump17.commands.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

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
