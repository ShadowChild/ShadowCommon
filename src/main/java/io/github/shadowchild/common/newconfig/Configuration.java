package io.github.shadowchild.common.newconfig;


import io.github.shadowchild.common.newconfig.parse.IniParser;
import io.github.shadowchild.common.newconfig.parse.JsonParser;
import io.github.shadowchild.common.newconfig.parse.PropertiesParser;
import io.github.shadowchild.common.util.Resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class Configuration {

    public static void initialiseConfiguration(Resource cfg, Object clazz, EnumConfig type) {

        Class clazz1 = clazz.getClass();

        Configurable a = (Configurable)clazz1.getAnnotation(Configurable.class);

        if(a == null) {

            throw new RuntimeException(String.format("Class %s does not have an @Configurable annotation"));
        }

        ConfigParser parser;
        Class<? extends ConfigParser> parserClass = type.getParser();
        try {

            Constructor ctor = parserClass.getConstructor(Resource.class);
            parser = (ConfigParser)ctor.newInstance(cfg);

            parser.parse(clazz);
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Configurable {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Option {

        String name();
        String category() default "General";
        String comment() default "";
        boolean isArray() default false;
        boolean optional() default false;
    }

    public enum EnumConfig {

        PROPERTIES(PropertiesParser.class),
        INI(IniParser.class),
        JSON(JsonParser.class);

        EnumConfig(Class<? extends ConfigParser> parser) {

            this.parser = parser;
        }

        private Class<? extends ConfigParser> parser;

        public Class<? extends ConfigParser> getParser() {

            return parser;
        }
    }
}
