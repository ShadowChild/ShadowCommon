package me.shadowchild.cybernize.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface JsonSerializable {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface FromJson {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface ToJson {}
}
