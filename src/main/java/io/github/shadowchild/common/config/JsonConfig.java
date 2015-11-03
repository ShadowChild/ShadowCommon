package io.github.shadowchild.common.config;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zach Piddock on 03/11/2015.
 */
class JsonConfig extends Config {

    private JsonObject jsonObject;

    public JsonConfig(String fileName) {

        super(fileName);
        this.jsonObject = loadJson();
    }

    public Object get(Type type, String category, String key, Object defaultValue, String comment) {

        // TODO: Implement this

        return null;
    }

    public JsonObject loadJson() {

        String truePath = "";
        String trueFileName = fileName;

        if (fileName.contains("/") || fileName.contains("\\")) {

            int slashIndex = !fileName.contains("/") ? fileName.indexOf("\\") : fileName.indexOf("/");
            truePath = fileName.substring(0, slashIndex);
            trueFileName = fileName.substring(slashIndex);
        }

        // Adds ".json" to a filename without one, allows for using "General" instead of "General.json"
        if (!trueFileName.contains(".json")) trueFileName.replace("", ".json");

        File file = new File("." + "/" + truePath, trueFileName);

        if (!file.exists()) {

            try {

                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();

                file.createNewFile();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        // TODO: Finish this

        return null;
    }
}
