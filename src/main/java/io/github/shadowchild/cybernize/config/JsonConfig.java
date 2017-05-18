package io.github.shadowchild.cybernize.config;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import io.github.shadowchild.cybernize.config.property.Property;
import io.github.shadowchild.cybernize.util.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Zach Piddock on 03/01/2016.
 */
public class JsonConfig extends Config {

    public JsonConfig(Resource location) {

        super(location, Type.JSON);
    }

    @Override
    protected Object createFile(File file) {

        try {

            return initConfig(file);
        } catch (IOException e) {

            e.printStackTrace();
        }
        JsonObject obj = new JsonObject();
        writeToDisk(file, obj);
        return obj;
    }

    @Override
    protected Object initConfig(File file) throws IOException {

        JsonObject object = new JsonObject();
        writeToDisk(file, object);

        if(file.exists()) {

            FileReader fr = new FileReader(file);
            JsonReader jr = new JsonReader(fr);

            JsonParser jp = new JsonParser();
            JsonElement element = jp.parse(jr);

            object = element.getAsJsonObject();
        } else {

            object = new JsonObject();
        }

        writeToDisk(file, object);
        return object;
    }

    private void writeToDisk(File file, JsonObject toWrite) {

        try {

            GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
            Gson gson = builder.create();
            String output = gson.toJson(toWrite);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(output.getBytes());
            fos.close();
        } catch(IOException e) {

            e.printStackTrace();
        }
    }

	@Override
	public <T> Property get(String section, String propertyName, T defaultValue, String comment) {
		// TODO Auto-generated method stub
		return null;
	}
}
