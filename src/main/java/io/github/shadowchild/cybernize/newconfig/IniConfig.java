package io.github.shadowchild.cybernize.newconfig;


import io.github.shadowchild.cybernize.util.Resource;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zach Piddock on 03/01/2016.
 */
public class IniConfig extends Config {

    public IniConfig(Resource location, Type type) {

        super(location, type);
    }

    @Override
    public String parseSection(String section) {

        return null;
    }

    @Override
    public Object createFile(File file) {

        Ini ini = new Ini();
        ini.setFile(file);
        return ini;
    }

    @Override
    public Object initConfig(File obj) throws IOException {

        ((Ini)config).load();
        return config;
    }
}
