package me.shadowchild.cybernize.resource;


import java.io.File;

/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class Resource {

    protected final String resourceType;
    protected final String location;
    protected boolean readOnly = false;

    public Resource(String resource) {

        this(resource, "");
    }

    public Resource(String resource, String parentDir) {

        String resourceType;
        String location;

        if(resource.contains(":")) {

            resourceType = resource.substring(0, resource.indexOf(":"));
            location = resource.substring(resource.indexOf(":") + 1);
        } else {

            // Set default resource type, sets to parentDir
            resourceType = "";
            location = resource;
        }
        this.resourceType = resourceType;
        this.location = parentDir + resourceType + "/" + location;
    }

    public String getLocation() {

        return location;
    }

    public String getResourceType() {

        return resourceType;
    }

    public boolean isReadOnly() {

        File file = toFile();
        if(file.exists() && file.canWrite()) return readOnly;

        return readOnly;
    }

    public boolean setReadOnly(boolean readOnly) {

        return setReadOnly(readOnly, false);
    }

    public boolean setReadOnly(boolean readOnly, boolean forceFileReadOnly) {

        this.readOnly = readOnly;
        if(forceFileReadOnly) {

            return toFile().setReadOnly();
        }
        return this.readOnly;
    }

    public File toFile() {

        return new File(".", getLocation());
    }
}
