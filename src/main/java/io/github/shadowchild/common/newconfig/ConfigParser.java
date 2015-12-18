package io.github.shadowchild.common.newconfig;


import io.github.shadowchild.common.util.IParser;
import io.github.shadowchild.common.util.Resource;

/**
 * Created by Zach Piddock on 17/12/2015.
 */
public abstract class ConfigParser implements IParser<Object> {

    public Resource resource;

    private ConfigParser() {}

    public ConfigParser(Resource resource) {

        this.resource = resource;
    }
}
