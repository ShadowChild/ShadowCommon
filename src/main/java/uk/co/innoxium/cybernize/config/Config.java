package uk.co.innoxium.cybernize.config;


import uk.co.innoxium.cybernize.config.property.Property;
import uk.co.innoxium.cybernize.resource.Resource;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zach Piddock on 31/12/2015.
 */
public abstract class Config {

    final Resource location;
    final String type;

    public static final Map<String, Class<? extends Config>> configTypeMap = new HashMap<>();

    static {

        configTypeMap.put("JSON", JsonConfig.class);
        configTypeMap.put("INI", IniConfig.class);
    }

    protected Object config;

    protected Config(Resource location, String type) {

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
     * Creates the file and returns the Object that will handle the config
     * @param file
     * @return
     */
    protected abstract Object createFile(File file);

    protected abstract Object initConfig(File file) throws IOException;
}
