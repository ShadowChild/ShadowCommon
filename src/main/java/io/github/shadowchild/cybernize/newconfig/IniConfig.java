package io.github.shadowchild.cybernize.newconfig;


import io.github.shadowchild.cybernize.util.Resource;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zach Piddock on 03/01/2016.
 */
public class IniConfig extends Config {

    public IniConfig(Resource location) {

        super(location, Type.INI);
    }

    @Override
    public String parseSection(String section) {

        return null;
    }

    @Override
    protected Object createFile(File file) {

        Ini ini = new Ini();
        ini.setFile(file);
        return ini;
    }

    @Override
    protected Object initConfig(File obj) throws IOException {

        ((Ini)config).load();
        return config;
    }
}
