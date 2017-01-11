package org.harmony.tes.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainerConfiguration {

    String[] value() default {};

    String[] locations() default {};

    Property[] properties() default {};

}
