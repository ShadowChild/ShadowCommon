package io.github.shadowchild.common.util;

import com.shc.silenceengine.input.Keyboard;

/**
 * Created by Zach Piddock on 30/10/2015.
 */
public class KeyboardUtils {

    public static boolean wasKeyReleased(int key) {

        return Keyboard.isClicked(key);
    }

    public static boolean wasKeyRelased(char key) {

        return wasKeyReleased((int)Character.toUpperCase(key));
    }
}
