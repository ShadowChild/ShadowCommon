package me.shadowchild.cybernize.setting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SettingsHolder {

    String id();

    Location location() default Location.WORKING_DIR;

    String ext() default "";

    enum Location {

        APPDATA,
        DOCUMENTS,
        WORKING_DIR
    }
}
