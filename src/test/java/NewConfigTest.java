import io.github.shadowchild.common.newconfig.Configuration;
import io.github.shadowchild.common.util.Resource;

/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class NewConfigTest {

    public static void main(String... args) {

        Resource urlJson = new Resource("https://raw.github.com/shadowchild/ShadowCommon/src/test/resources/json.json", true);
        Resource ini = new Resource("ini.ini");
        Resource props = new Resource("props.properties");

        Configuration.initialiseConfiguration(urlJson, new Props(), Configuration.EnumConfig.JSON);
        Configuration.initialiseConfiguration(ini, new Props(), Configuration.EnumConfig.INI);
        Configuration.initialiseConfiguration(props, new Props(), Configuration.EnumConfig.PROPERTIES);
    }

    @Configuration.Configurable
    static class Props {

        @Configuration.Option(name = "StringOption")
        public String str = "defaultValue";

        @Configuration.Option(name = "BooleanOption", category = "Bools")
        public boolean bool = true;

        @Configuration.Option(name = "ShortOption")
        public short shrt = 9;

        @Configuration.Option(name = "FloatOption")
        public float flt = 3.14f;

        @Configuration.Option(name = "IntOption")
        public int anInt = 43423;

        @Configuration.Option(name = "DoubleOption")
        public double dbl = Math.PI;

        @Configuration.Option(name = "ByteOption", category = "Net")
        public byte byt = 9;
    }
}
