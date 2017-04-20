import io.github.shadowchild.cybernize.config.Config;
import io.github.shadowchild.cybernize.config.Configuration;

/**
 * Created by Zach Piddock on 07/11/2015.
 */
public class IniTest {

    public static void main(String... args) {

        Config conf = new Configuration(Configuration.ConfigType.INI, "test/ini").load();

        // Test both comment and non comment
        String string = conf.getString("Test", "StringTest", "TestValue", "This is a comment");
        int integ = conf.getInteger("Test", "IntegerTest", 1, null);
        // Test both comment
        byte byt = conf.getByte("Test2", "ByteTest", (byte)1, "This is a comment");
        double doub = conf.getDouble("Test2", "DoubleTest", 2.2, "This is a comment");
        // Test both non comment
        float floa = conf.getFloat("Test3", "FloatTest", 2.4f, null);
        short shor = conf.getShort("Test3", "ShortTest", (short)0, null);
        // Test last two types
        long lon = conf.getLong("Test4", "LongTest", 43624328, null);
        boolean bool = conf.getBoolean("Test4", "BooleanTest", false, null);

        System.out.println("String test = " + string);
        System.out.println("Integer test = " + integ);
        System.out.println("Byte test = " + byt);
        System.out.println("Double test = " + doub);
        System.out.println("Float test = " + floa);
        System.out.println("Short test = " + shor);
        System.out.println("Long test = " + lon);
        System.out.println("Boolean test = " + bool);
    }
}
