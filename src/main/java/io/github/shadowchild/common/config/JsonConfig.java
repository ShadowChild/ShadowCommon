package io.github.shadowchild.common.config;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by Zach Piddock on 03/11/2015.
 */
class JsonConfig extends Config {

    private JsonObject root;
    private File file;

    public JsonConfig(String fileName) {

        super(fileName);
        this.root = loadJson();
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
        if (!trueFileName.contains(".json")) trueFileName += ".json";

        File file = new File("." + "/" + truePath, trueFileName);
        this.file = file;

        if (!file.exists()) {

            try {

                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();

                file.createNewFile();

                return createJsonObject(false, file);
            } catch (IOException e) {

                e.printStackTrace();
                return new JsonObject();
            }
        } else {

            try {

                return createJsonObject(true, file);
            } catch(IOException e) {

                try {

                    File backup = new File(file.getParent(), trueFileName + ".json");
                    if (backup.exists()) {

                        backup.delete();
                    }
                    FileUtils.copyFile(file, backup, false);
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
                e.printStackTrace();
                return new JsonObject();
            }
        }
    }

    private JsonObject createJsonObject(boolean exists, File file) throws FileNotFoundException {

        if(exists) {

            FileReader fr = new FileReader(file);
            JsonReader jr = new JsonReader(fr);

            JsonParser jp = new JsonParser();
            JsonElement element = jp.parse(jr);

            return element.getAsJsonObject();
        } else {

            return new JsonObject();
        }
    }

    public Object get(Type type, String category, String key, Object defaultValue, String comment) {

        // TODO: Implement arrays
        JsonObject cat = checkCategoryExists(root, category) ? root.getAsJsonObject(category) : newObject(category);

        // If the category has the key, obtain and return it
        if(cat.has(key)) {

            JsonElement element = cat.get(key);
            if(element instanceof JsonObject) {

                if(((JsonObject) element).has("Value")) {

                    return ((JsonObject) element).get("Value");
                }
                return element;
            }
            return element;
        }

        // if not, attempt to write it to file
        if(comment != null && !comment.isEmpty()) {

            JsonObject commentedValue = new JsonObject();
            writeProperty(commentedValue, "Value", defaultValue);
            commentedValue.addProperty("Comment", comment);
            cat.add(key, commentedValue);

        } else {

            writeProperty(cat, key, defaultValue);
        }

        writeToDisk(file, root);

        return defaultValue;
    }

    private void writeToDisk(File file, JsonObject toWrite) {

        try {

            GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
            Gson gson = builder.create();
            String output = gson.toJson(toWrite);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(output.getBytes());
            fos.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private boolean checkCategoryExists(JsonObject jsonObject, String category) {

        JsonElement element = jsonObject.getAsJsonObject(category);
        return element != null;
    }

    private JsonObject newObject(String category) {

        JsonObject obj = new JsonObject();
        root.add(category, obj);
        return obj;
    }

    private void writeProperty(JsonObject obj, String key, Object value) {

        if(value instanceof Number) {

            obj.addProperty(key, (Number)value);
        } else if (value instanceof Boolean) {

            obj.addProperty(key, (Boolean)value);
        } else if(value instanceof Character) {

            obj.addProperty(key, (Character)value);
        } else if (value instanceof String) {

            obj.addProperty(key, (String)value);
        }
    }

    @Override
    public byte getByte(String category, String key, byte defaultValue, String comment) {

        return ((JsonPrimitive) get(Type.BYTE, category, key, defaultValue, comment)).getAsByte();
    }

    @Override
    public double getDouble(String category, String key, double defaultValue, String comment) {

        return ((JsonPrimitive) get(Type.DOUBLE, category, key, defaultValue, comment)).getAsDouble();
    }

    @Override
    public String getString(String category, String key, String defaultValue, String comment) {

        return ((JsonPrimitive) get(Type.STRING, category, key, defaultValue, comment)).getAsString();
    }

    @Override
    public int getInteger(String category, String key, int defaultValue, String comment) {

        return ((JsonPrimitive) get(Type.INT, category, key, defaultValue, comment)).getAsInt();
    }

    @Override
    public short getShort(String category, String key, short defaultValue, String comment) {

        return ((JsonPrimitive) get(Type.SHORT, category, key, defaultValue, comment)).getAsShort();
    }

    @Override
    public long getLong(String category, String key, long defaultValue, String comment) {

        return ((JsonPrimitive) get(Type.LONG, category, key, defaultValue, comment)).getAsLong();
    }

    @Override
    public float getFloat(String category, String key, float defaultValue, String comment) {

        return ((JsonPrimitive) get(Type.FLOAT, category, key, defaultValue, comment)).getAsFloat();
    }

    @Override
    public boolean getBoolean(String category, String key, boolean defaultValue, String comment) {

        return ((JsonPrimitive) get(Type.BOOL, category, key, defaultValue, comment)).getAsBoolean();
    }
}
