package io.github.shadowchild.common.newconfig.parse;


import io.github.shadowchild.common.newconfig.ConfigParser;
import io.github.shadowchild.common.util.Resource;

/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class IniParser extends ConfigParser {

    public IniParser(Resource resource) {

        super(resource);
    }

    @Override
    public void parse(Object... parseable) {

        System.out.println("Parsing INI");
    }
}
