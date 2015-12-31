package io.github.shadowchild.common.newconfig;


/**
 * Created by Zach Piddock on 31/12/2015.
 */
public class ConfigBuilder {

    EnumConfigType type;

    public ConfigBuilder(EnumConfigType type) {

        this.type = type;
    }

    public Config build() {

        Config cfg = null;

        Class clazz = type.getClazz();

        return cfg;
    }
}
