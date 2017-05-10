package io.github.shadowchild.cybernize.newconfig.property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpidd on 10/05/2017.
 */
public class Property<T> {

    private T value;
    private List<String> comments;

    public Property(T value) {

        this.value = value;
        this.comments = new ArrayList<>();
    }

    public T get() {

        return value;
    }

    public List<String> getComments() {

        return comments;
    }

    public void setComments(List<String> comments) {

        this.comments = comments;
    }

    public void addComment(String comment) {

        if(this.comments == null) {

            setComments(new ArrayList<>());
        }
        getComments().add(comment);
    }
}
