package io.github.shadowchild.common.newconfig;


import java.util.List;

/**
 * Created by Zach Piddock on 31/12/2015.
 */
public class Property {

    public Object value;

    public Property(Object value) {

        this.value = value;
    }

    public String getAsString() {

        return (String)value;
    }

    public short getAsShort() {

        return (short)value;
    }

    public int getAsInt() {

        return (int)value;
    }

    public double getAsDouble() {

        return (double)value;
    }

    public float getAsFloat() {

        return (float)value;
    }

    public byte getAsByte() {

        return (byte)value;
    }

    public long getAsLong() {

        return (long)value;
    }

    public boolean getAsBoolean() {

        return (boolean)value;
    }

    public List getAsList() {

        return (List)value;
    }

    public enum EnumPropType {

        INT,
        BOOL,
        STRING,
        LONG,
        DOUBLE,
        BYTE,
        SHORT,
        FLOAT,
        ARRAY
    }
}
