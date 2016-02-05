package io.github.shadowchild.common.util;


/**
 * Created by Zach Piddock on 24/01/2016.
 */
public class UrlResource extends Resource {

    public UrlResource(String resource) {

        super(resource);
        this.readOnly = true;
    }

    @Override
    public void setReadOnly(boolean readOnly, boolean forceFileReadOnly) {

        // Do not do anything, you can't change a url resource
    }

    @Override
    public void setReadOnly(boolean readOnly) {

        // Do not do anything, you can't change a url resource
    }

    @Override
    public boolean isReadOnly() {

        return true;
    }
}
