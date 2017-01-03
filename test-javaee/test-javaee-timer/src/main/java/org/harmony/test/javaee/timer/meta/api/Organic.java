package org.harmony.test.javaee.timer.meta.api;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Metatype
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@Singleton
@Lock(LockType.READ)
public @interface Organic {

}
