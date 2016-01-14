package io.github.shadowchild.common.newconfig;


import io.github.shadowchild.common.util.Resource;

/**
 * Created by Zach Piddock on 31/12/2015.
 */
public abstract class Config {

    final Resource location;
    final EnumConfigType type;

    public Config(Resource location, EnumConfigType type) {

        this.location = location;
        this.type = type;
        load();
    }

    public void load() {

    }

    public Property get(Property.EnumPropType type, String section, String propertyName, Object defaultValue) {

        return get(type, section, propertyName, defaultValue, null);
    }

    public Property get(Property.EnumPropType type, String section, String propertyName, Object defaultValue, String comment) {

        boolean _comment = comment != null;
        String _section = parseSection(section);

        return null;
    }

    /**
     * Parses the passed section and returns the section relevant to the config type
     *
     * @param section
     * @return
     */
    public abstract String parseSection(String section);
}
