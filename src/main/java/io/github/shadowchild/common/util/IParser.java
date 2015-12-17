package io.github.shadowchild.common.util;


/**
 * Created by Zach Piddock on 17/12/2015.
 */
public interface IParser<T> {

    void parse(T... parseable);
}
