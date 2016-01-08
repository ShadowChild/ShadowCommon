package io.github.shadowchild.common.util;


/**
 * Created by Zach Piddock on 17/12/2015.
 */
public interface IParser<T> {

    default void parseMultiple(T... parseable) {

        for(T p : parseable) {

            parse(p);
        }
    }

    void parse(T parseable);
}
