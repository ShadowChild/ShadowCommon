package io.github.shadowchild.cybernize.util;


import java.io.File;

/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class Resource {

    protected final String resourceType;
    protected final String location;
    protected boolean readOnly = false;

    public Resource(String resource) {

        String resourceType;
        String location;

        if(resource.contains(":")) {

            resourceType = resource.substring(0, resource.indexOf(":"));
            location = resource.substring(resource.indexOf(":") + 1);
        } else {

            resourceType = "textures";
            location = resource;
        }
        this.resourceType = resourceType;
        this.location = "/assets/" + resourceType + "/" + location;
    }

    public String getLocation() {

        return location;
    }

    public String getResourceType() {

        return resourceType;
    }

    public boolean isReadOnly() {

        File file = new File(location);
        if(file.exists() && file.canWrite()) return readOnly;

        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {

        setReadOnly(readOnly, false);
    }

    public void setReadOnly(boolean readOnly, boolean forceFileReadOnly) {

        this.readOnly = readOnly;
        if(forceFileReadOnly) {

            File file = new File(location);
            file.setReadOnly();
        }
    }
}
