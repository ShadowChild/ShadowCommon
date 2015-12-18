package io.github.shadowchild.common.util;


/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class Resource {

    private final String location;
    private final boolean isURL;
    private boolean readOnly = false;

    public Resource(String location, boolean isURL) {

        this.location = location;
        this.isURL = isURL;
    }

    public Resource(String location) {

        this(location, false);
    }

    public String getLocation() {

        return location;
    }

    public boolean isURL() {

        return isURL;
    }

    public boolean isReadOnly() {

        return isURL || readOnly;
    }

    public void setReadOnly(boolean readOnly) {

        this.readOnly = readOnly;
    }
}
