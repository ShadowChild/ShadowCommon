package me.shadowchild.cybernize.util;


/**
 * Created by Zach Piddock on 17/12/2015.
 */
public interface IParser<T> {

    default void parseMultiple(T... parsable) {

        for(T p : parsable)
            parse(p);
    }

    void parse(T parsable);
}
