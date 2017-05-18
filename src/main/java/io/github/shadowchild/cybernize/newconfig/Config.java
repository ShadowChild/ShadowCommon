package io.github.shadowchild.cybernize.newconfig;


import io.github.shadowchild.cybernize.newconfig.property.Property;
import io.github.shadowchild.cybernize.util.Resource;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zach Piddock on 31/12/2015.
 */
public abstract class Config {

    final Resource location;
    final Type type;

    protected Object config;

    protected Config(Resource location, Type type) {

        this.location = location;
        this.type = type;
        load();
    }

    public void load() {

        File file = location.toFile();

        if(!file.exists()) {

            try {

                if(!file.getParentFile().exists()) file.getParentFile().mkdirs();

                file.createNewFile();

                config = createFile(file);

                initConfig(file);
            } catch(IOException e) {

                e.printStackTrace();
                createFile(file);
            }
        } else {

            config = createFile(file);

            try {

                initConfig(file);
            } catch(IOException e) {

                try {

                    File backup = file;
                    if(backup.exists()) {

                        backup.delete();
                    }
                    FileUtils.copyFile(file, backup, false);
                } catch(IOException e1) {

                    e1.printStackTrace();
                }
                e.printStackTrace();
                createFile(file);
            }
        }
    }

    public <T> Property get(String section, String propertyName, T defaultValue) {

        return get(section, propertyName, defaultValue, null);
    }

    public abstract <T> Property get(String section, String propertyName, T defaultValue, String comment);

    /**
     * Parses the passed section and returns the section relevant to the config type
     *
     * @param section
     */
    public abstract void handleSection(String section);

    /**
     * Creates the file and returns the Object that will handle the config
     * @param file
     * @return
     */
    protected abstract Object createFile(File file);

    protected abstract Object initConfig(File file) throws IOException;

    public enum Type {

        JSON(JsonConfig.class),
        INI(IniConfig.class);

        private Class<? extends Config> clazz;

        Type(Class<? extends Config> clazz) {

            this.clazz = clazz;
        }

        public Class<? extends Config> getClazz() {

            return clazz;
        }
    }
}
