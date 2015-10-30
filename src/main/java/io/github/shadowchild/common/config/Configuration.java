package io.github.shadowchild.common.config;

import com.sun.istack.internal.NotNull;
import org.apache.commons.io.FileUtils;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Zach Piddock on 22/10/2015.
 */
public class Configuration {

    public enum Type{

        INT, BOOL, STRING, LONG, DOUBLE, BYTE, SHORT, FLOAT
    }

    private final String FILE_NAME;
    public Ini ini;

    public Configuration(@NotNull String FILE_NAME) {

        this.FILE_NAME = FILE_NAME;
        this.ini = loadIni();
    }

    private Ini loadIni(){

        String truePath = "";
        String trueFileName = FILE_NAME;

        if(FILE_NAME.contains("/") || FILE_NAME.contains("\\")) {

            int slashIndex = !FILE_NAME.contains("/") ? FILE_NAME.indexOf("\\") : FILE_NAME.indexOf("/");
            truePath = FILE_NAME.substring(0, slashIndex);
            trueFileName = FILE_NAME.substring(slashIndex);
        }

        // Adds ".ini" to a filename without one, allows for using "General" instead of "General.ini"
        if(!trueFileName.contains(".ini")) trueFileName.replace("", ".ini");

        File file = new File("." + "/" + truePath, trueFileName);

        if(!file.exists()) {

            try{

                if(!file.getParentFile().exists()) file.getParentFile().mkdirs();

                file.createNewFile();

                return createIni(file);
            }catch(IOException e){

                //MOD.getLogger().error("Cannot create config file, this shouldn't happen.");
                e.printStackTrace();
                return createIni(file);
            }
        } else {

            Ini ini = createIni(file);

            try{

                ini.load(file);
                return ini;
            }catch(IOException e){

                //MOD.getLogger().error("Could not load config file, making backup then loading defaults.");
                try{

                    File backup = new File(file.getParent(), trueFileName + ".ini");
                    if(backup.exists()){

                        backup.delete();
                    }
                    FileUtils.copyFile(file, backup, false);
                }catch(IOException e1){

                    e1.printStackTrace();
                }
                e.printStackTrace();
                return createIni(file);
            }
        }
    }

    private Ini createIni(File file){

        Ini ini = new Ini();
        ini.setFile(file);
        return ini;
    }

    private boolean checkSectionExists(Ini ini, String section){

        Set<String> sections = ini.keySet();

        for(String section1 : sections){

            if(section1.equals(section)) return true;
        }
        return false;
    }

    private Object get(Type type, String catergory, String key, Object defValue, String comment) {

        Ini.Section section = checkSectionExists(ini, catergory) ? ini.get(catergory) : ini.add(catergory);
        String stringVal = section.get(key);
        if(stringVal == null || stringVal.isEmpty()) {

            stringVal = defValue + "";
            section.add(key, stringVal);
            try{

                ini.store();
            }catch(IOException e){

                e.printStackTrace();
            }
        }
        if(comment != null && !comment.isEmpty()){

            section.putComment(key, " " + comment);
            try {

                ini.store();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        Object returnValue = section.fetch(key);
        String val = (String)returnValue;
        switch(type){

            case INT: returnValue = Integer.valueOf(val); break;

            case SHORT: returnValue = Short.valueOf(val); break;

            case LONG: returnValue = Long.valueOf(val); break;

            case BYTE: returnValue = Byte.valueOf(val); break;

            case DOUBLE: returnValue = Double.valueOf(val); break;

            case FLOAT: returnValue = Float.valueOf(val); break;

            case BOOL: returnValue = Boolean.valueOf(val); break;

            case STRING: break;
        }
        return returnValue;
    }

    public int getInteger(String category, String key, int defaultValue, String comment) {

        return (Integer)get(Type.INT, category, key, defaultValue, comment);
    }

    public short getShort(String category, String key, short defaultValue, String comment) {

        return (Short)get(Type.SHORT, category, key, defaultValue, comment);
    }

    public long getLong(String category, String key, long defaultValue, String comment) {

        return (Long)get(Type.LONG, category, key, defaultValue, comment);
    }

    public byte getByte(String category, String key, byte defaultValue, String comment) {

        return (Byte)get(Type.BYTE, category, key, defaultValue, comment);
    }

    public double getDouble(String category, String key, double defaultValue, String comment) {

        return (Double)get(Type.DOUBLE, category, key, defaultValue, comment);
    }

    public float getFloat(String category, String key, float defaultValue, String comment) {

        return (Float)get(Type.FLOAT, category, key, defaultValue, comment);
    }

    public boolean getBoolean(String category, String key, boolean defaultValue, String comment) {

        return (Boolean)get(Type.BOOL, category, key, defaultValue, comment);
    }

    public String getString(String category, String key, String defaultValue, String comment) {

        return (String)get(Type.STRING, category, key, defaultValue, comment);
    }
}