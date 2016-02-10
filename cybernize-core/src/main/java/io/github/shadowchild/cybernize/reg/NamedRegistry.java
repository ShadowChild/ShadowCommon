package io.github.shadowchild.cybernize.reg;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zach Piddock on 16/01/2016.
 */
public class NamedRegistry<T> {
	
	private boolean ignoreValue;

    public Map<String, T> registry = new HashMap<>();

    public int register(String name, T object) {

        if(registry.containsKey(name)) {

            return KEY_REGISTERED;
        } else if(registry.containsValue(object) && !ignoreValue) {

            return VALUE_REGISTERED;
        } else {

            registry.put(name, object);
            return SUCCESS;
        }
    }
    
    public void remove(String name) {
    	
    	registry.remove(name);
    }

    public T getObject(String name) {

        if(registry.containsKey(name)) {

            return registry.get(name);
        }
        return null;
    }
    
    public void clearRegistry() {
    	
    	registry.clear();
    }
    
    public void setIgnoreValueOnRegister(boolean ignoreValue) {
    	
    	this.ignoreValue = ignoreValue;
    }

    public static final int SUCCESS = 0, KEY_REGISTERED = 1, VALUE_REGISTERED = 2;
}
