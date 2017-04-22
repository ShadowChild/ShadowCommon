package io.github.shadowchild.cybernize.util;


/**
 * Created by Zach Piddock on 24/01/2016.
 */
public class UrlResource extends Resource {

    public UrlResource(String resource) {

        super(resource);
        this.readOnly = true;
    }

    @Override
    public boolean setReadOnly(boolean readOnly, boolean forceFileReadOnly) {

        // return true as a URL is always read only
        return true;
    }

    @Override
    public boolean setReadOnly(boolean readOnly) {

        // return true as a URL is always read only
        return true;
    }

    @Override
    public boolean isReadOnly() {

        return true;
    }
}
