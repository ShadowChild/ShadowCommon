package io.github.shadowchild.common.newconfig;

import io.github.shadowchild.common.config.IniConfig;
import io.github.shadowchild.common.config.JsonConfig;

/**
 * Created by Zach Piddock on 31/12/2015.
 */
public enum EnumConfigType {

    JSON(JsonConfig.class),
    INI(IniConfig.class);

    private Class<? extends Config> clazz;

    EnumConfigType(Class<? extends Config> clazz) {

        this.clazz = clazz;
    }

    public Class<? extends Config> getClazz() {

        return clazz;
    }
}
