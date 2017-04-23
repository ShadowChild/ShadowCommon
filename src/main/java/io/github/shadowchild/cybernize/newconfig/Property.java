package io.github.shadowchild.cybernize.newconfig;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach Piddock on 31/12/2015.
 */
public class Property {

    public Object value;
    public List<String> comments;

    public Property(Object value) {

        this.value = value;
        this.comments = new ArrayList<>();
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
    
    public Property addComment(String comment) {
    	
    	comments.add(comment);
    	return this;
    }

    public List<String> getComments() {
    	
		return comments;
	}

	public enum Type {

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
