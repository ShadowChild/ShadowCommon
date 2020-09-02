package uk.co.innoxium.cybernize.util;

public class ClassLoadUtil {

    public static ClassLoader getCL() {

        return ClassLoadUtil.class.getClassLoader();
    }

    public static ClassLoader getSafeCL() {

        return Thread.currentThread().getContextClassLoader();
    }

    public static <T> Class<T> loadClass(String name) throws ClassNotFoundException {

        return (Class<T>) getCL().loadClass(name);
    }
}
