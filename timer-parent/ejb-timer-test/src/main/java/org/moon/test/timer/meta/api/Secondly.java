package org.moon.test.timer.meta.api;

import javax.ejb.Schedule;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Metatype
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Secondly {

    public static interface $ {

        @Secondly
        @Schedule(second = "*", minute = "*", hour = "*")
        public void method();
    }
}