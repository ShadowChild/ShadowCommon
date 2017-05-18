package io.github.shadowchild.cybernize.newconfig;


import io.github.shadowchild.cybernize.newconfig.property.Property;
import io.github.shadowchild.cybernize.util.Resource;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zach Piddock on 03/01/2016.
 */
public class IniConfig extends Config {

    public IniConfig(Resource location) {

        super(location, Type.INI);
    }

    @Override
    public void handleSection(String section) {

        Ini ini = (Ini)config;
        if(!ini.containsKey(section)) {
        	
        	ini.add(section);
        }
    }

    @Override
    protected Object createFile(File file) {

        Ini ini = new Ini();
        ini.setFile(file);
        return ini;
    }

    @Override
    protected Object initConfig(File obj) throws IOException {

        ((Ini)config).load();
        return config;
    }

	@Override
	public <T> Property get(String section, String propertyName, T defaultValue, String comment) {
		
		Property<T> property = new Property<>(defaultValue);
		property.addComment(comment);
		
		Ini ini = (Ini)config;
		if(!ini.containsKey(section)) {
			
			ini.add(section);
		}
		Ini.Section _section = ini.get(section);
		
		if(comment != null && !comment.isEmpty()) {

            _section.putComment(propertyName, " " + comment);
            storeIni(ini);
		}
		
		if(_section.containsKey(propertyName)) {
			
			Object ret = _section.fetch(propertyName);
			property.setValue((T)ret);	
		} else {
			
			_section.add(propertyName, defaultValue);
			storeIni(ini);
		}
		return property;
	}
	
	private void storeIni(Ini ini) {
		
		try {
			
			ini.store();
		} catch(IOException e) {
			
			e.printStackTrace();
		}
	}
}
