package uk.co.innoxium.cybernize.json;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonUtil {

    public static JsonElement getElementFromUrl(URL url) throws IOException {

        URLConnection con = url.openConnection();
        con.connect();
        if(con instanceof HttpURLConnection) {

            // Make sure response code is in the 200 range.
            if (((HttpURLConnection)con).getResponseCode() / 100 != 2) {

                throw new IOException("Could not connect to the server, error code: " + ((HttpURLConnection)con).getResponseCode());
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        JsonReader jr = new JsonReader(br);
        return JsonParser.parseReader(jr);
    }

    public static JsonObject getObjectFromUrl(URL url) throws IOException {

        return getElementFromUrl(url).getAsJsonObject();
    }

    public static JsonElement getElementFromPath(Path path) throws IOException {

        BufferedReader br = Files.newBufferedReader(path);
        JsonReader jr = new JsonReader(br);

        return JsonParser.parseReader(jr);
    }

    public static JsonObject getObjectFromPath(Path path) throws IOException {

        return getElementFromPath(path).getAsJsonObject();
    }

    public static String getString(JsonObject obj, String name) {

        return obj.get(name).getAsString();
    }

    public static JsonObject getObject(JsonObject obj, String name) {

        return obj.getAsJsonObject(name);
    }

    public static byte getByte(JsonObject obj, String name) {

        return obj.getAsJsonPrimitive(name).getAsByte();
    }

    public static int getInt(JsonObject obj, String name) {

        return obj.getAsJsonPrimitive(name).getAsInt();
    }

    public static char getChar(JsonObject obj, String name) {

        return obj.getAsJsonPrimitive(name).getAsCharacter();
    }

    public static double getDouble(JsonObject obj, String name) {

        return obj.getAsJsonPrimitive(name).getAsDouble();
    }

    public static float getFloat(JsonObject obj, String name) {

        return obj.getAsJsonPrimitive(name).getAsFloat();
    }

    public static short getShort(JsonObject obj, String name) {

        return obj.getAsJsonPrimitive(name).getAsShort();
    }

    public static long getLong(JsonObject obj, String name) {

        return obj.getAsJsonPrimitive(name).getAsLong();
    }

    public static boolean getBool(JsonObject obj, String name) {

        return obj.getAsJsonPrimitive(name).getAsBoolean();
    }

    public static BigInteger getBigInt(JsonObject obj, String name) {

        return obj.get(name).getAsBigInteger();
    }

    public static BigDecimal getBigDec(JsonObject obj, String name) {

        return obj.get(name).getAsBigDecimal();
    }

    public static JsonArray getArray(JsonObject obj, String name) {

        return obj.getAsJsonArray(name);
    }

    public static Gson getGson() {

        return new GsonBuilder().setPrettyPrinting().create();
    }

    public static FileWriter getFileWriter(File file) throws IOException {

        return new FileWriter(file);
    }

    // Almost identical to FileUtil.getFile(), but initializes it as a valid json
    public static File getJsonFile(File path, boolean isFolder) {

        synchronized (path) {

            if (!path.exists()) {

                path.getParentFile().mkdirs();
                if (isFolder) path.mkdir();
                else {

                    try {

                        path.createNewFile();
                        JsonObject obj = new JsonObject();
                        FileWriter writer = getFileWriter(path);
                        getGson().toJson(obj, writer);
                        writer.close();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        }
        return path;
    }
}
