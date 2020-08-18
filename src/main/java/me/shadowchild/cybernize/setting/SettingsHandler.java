package me.shadowchild.cybernize.setting;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileConfig;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SettingsHandler {

    public static final ArrayList<Class> HOLDERS = new ArrayList<>();
    private static final HashMap<Class, CommentedFileConfig> CONFIG_MAP = new HashMap<>();

    static {

        addRuntimeHook();
    }

    public static void addHolder(Class clazz) throws IllegalStateException {

        // Check if the class contains the SettingsHolder annotation
        if(clazz.isAnnotationPresent(SettingsHolder.class) && checkHasConfig(clazz)) {

            HOLDERS.add(clazz);
        } else {

            throw new IllegalStateException(String.format("Class %s is not a valid SettingsHolder, exiting.", clazz.getCanonicalName()));
        }
    }

    public static void load() throws IllegalAccessException {

        for(Class clazz : HOLDERS) {

            SettingsHolder annot = (SettingsHolder)clazz.getDeclaredAnnotation(SettingsHolder.class);
            String id = annot.id();

            System.out.println(id);

            CommentedFileConfig cfg = null;

            for(Method method : clazz.getDeclaredMethods()) {

                if(method.isAnnotationPresent(Config.class)) {

                    // Initialise the cfg value
                    try {

                        cfg = (CommentedFileConfig) method.invoke(new Object[0]);
                    } catch (IllegalAccessException | InvocationTargetException e) {

                        // Load default if the method errors out
                        e.printStackTrace();
                        cfg = CommentedFileConfig
                                .builder(new File("./config/", id + "toml"))
                                .autoreload()
                                .autosave()
                                .charset(StandardCharsets.UTF_8)
                                .onFileNotFound((file, cfgFormat) -> {

                                    Files.createDirectories(file.getParent());
                                    Files.createFile(file);
                                    cfgFormat.initEmptyFile(file);
                                    return false;
                                }).build();
                    }
                    CONFIG_MAP.put(clazz, cfg);
                }
            }

            // check if cfg is not null
            if(cfg != null) {

                // We can now load the config
                cfg.load();

                // Set the settings
                for(Field field : clazz.getDeclaredFields()) {

                    // only execute if field has @Setting, otherwise ignore
                    if(field.isAnnotationPresent(Setting.class)) {

                        // field has @Setting
                        field.setAccessible(true);

                        Setting setting = field.getAnnotation(Setting.class);
                        String settingName = setting.category() + "." + field.getName();
                        Object val = field.get(clazz);

                        // Attempt to get the value from the cfg file, else load the default value
                        Object newValue = cfg.getOrElse(settingName, val);
                        saveValueToConfig(cfg, settingName, newValue);
                        // Attempt to write a comment
                        if(field.isAnnotationPresent(Setting.Comment.class))
                            cfg.setComment(settingName, field.getAnnotation(Setting.Comment.class).value());
                        field.set(clazz, newValue);
                    }
                }

                // Save the config
                cfg.save();
            }
        }
    }

    public static void addRuntimeHook() {

        System.out.println("CYBERNIZE: Flushing Settings to disk for shutdown...");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            CONFIG_MAP.forEach((clazz, cfg) -> {

                try {
                    // Set the settings
                    for (Field field : clazz.getDeclaredFields()) {

                        // only execute if field has @Setting, otherwise ignore
                        if (field.isAnnotationPresent(Setting.class)) {

                            // field has @Setting
                            field.setAccessible(true);

                            Setting setting = field.getAnnotation(Setting.class);
                            String settingName = setting.category() + "." + field.getName();
                            Object val = field.get(clazz);

                            // Attempt to get the value from the cfg file, else load the default value
                            Object newValue = cfg.getOrElse(settingName, val);
                            saveValueToConfig(cfg, settingName, newValue);
                            // Attempt to write a comment
                            if (field.isAnnotationPresent(Setting.Comment.class))
                                cfg.setComment(settingName, field.getAnnotation(Setting.Comment.class).value());
                            field.set(clazz, newValue);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                cfg.save();
                cfg.close();
            });
        }));
    }

    private static boolean checkHasConfig(Class clazz) {

        for(Method method : clazz.getDeclaredMethods()) {

            if(method.isAnnotationPresent(Config.class)) return true;
        }
        return false;
    }

    private static void saveValueToConfig(CommentedFileConfig cfg, String name, Object value) {

        cfg.set(name, value);
    }
}
