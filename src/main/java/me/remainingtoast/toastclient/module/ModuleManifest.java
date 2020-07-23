package me.remainingtoast.toastclient.module;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModuleManifest {
    String label();
    int key() default -1;
    String[] aliases() default {};
    Module.Category category();
    boolean hidden() default false;
    String description() default "";
}
