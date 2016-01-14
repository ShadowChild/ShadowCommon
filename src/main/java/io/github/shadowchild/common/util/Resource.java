package io.github.shadowchild.common.util;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class Resource {

    private final String location;
    private final boolean isURL;
    private boolean readOnly = false;

    public Resource(String location) {

        boolean b;

        try {

            new URL(location);
            // didnt cause and exception so assume it's a real URL
            b = true;
        } catch(MalformedURLException e) {

            b = false;
        }

        this.isURL = b;
        this.location = location;
    }

    public String getLocation() {

        return location;
    }

    public boolean isURL() {

        return isURL;
    }

    public boolean isReadOnly() {

        if(!isURL) {

            File file = new File(location);
            if(file.exists() && file.canWrite()) return readOnly;
        }

        return isURL || readOnly;
    }

    public void setReadOnly(boolean readOnly) {

        setReadOnly(readOnly, false);
    }

    public void setReadOnly(boolean readOnly, boolean forceFileReadOnly) {

        this.readOnly = readOnly;
        if(forceFileReadOnly && !isURL) {

            File file = new File(location);
            file.setReadOnly();
        }
    }
}
