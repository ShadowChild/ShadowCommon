package io.github.shadowchild.common.config;

/**
 * Created by Zach Piddock on 03/11/2015.
 */
public abstract class Config {

    protected String fileName;

    public Config(String fileName) {

        this.fileName = fileName;
    }

    public enum Type {

        INT, BOOL, STRING, LONG, DOUBLE, BYTE, SHORT, FLOAT
    }

    public abstract Object get(Type type, String category, String key, Object defaultValue, String comment);

    public int getInteger(String category, String key, int defaultValue, String comment) {

        return (Integer) get(Type.INT, category, key, defaultValue, comment);
    }

    public short getShort(String category, String key, short defaultValue, String comment) {

        return (Short) get(Type.SHORT, category, key, defaultValue, comment);
    }

    public long getLong(String category, String key, long defaultValue, String comment) {

        return (Long) get(Type.LONG, category, key, defaultValue, comment);
    }

    public byte getByte(String category, String key, byte defaultValue, String comment) {

        return (Byte) get(Type.BYTE, category, key, defaultValue, comment);
    }

    public double getDouble(String category, String key, double defaultValue, String comment) {

        return (Double) get(Type.DOUBLE, category, key, defaultValue, comment);
    }

    public float getFloat(String category, String key, float defaultValue, String comment) {

        return (Float) get(Type.FLOAT, category, key, defaultValue, comment);
    }

    public boolean getBoolean(String category, String key, boolean defaultValue, String comment) {

        return (Boolean) get(Type.BOOL, category, key, defaultValue, comment);
    }

    public String getString(String category, String key, String defaultValue, String comment) {

        return (String) get(Type.STRING, category, key, defaultValue, comment);
    }
}
