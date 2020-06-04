import io.github.shadowchild.cybernize.config.Config;
import io.github.shadowchild.cybernize.config.IniConfig;
import io.github.shadowchild.cybernize.config.JsonConfig;
import io.github.shadowchild.cybernize.config.property.Property;
import io.github.shadowchild.cybernize.resource.Resource;

public class NewConfigTest {

	public static void main(String[] args) {
	
		Config conf;
		
		// Test INI Config
		conf = new IniConfig(new Resource("test:INITest.ini"));
		
		testConf(conf);
		
		// Test JSON Config
		conf = new JsonConfig(new Resource("test:JSONConfig.json"));
	}
	
	private static void testConf(Config conf) {
		
		Property<String> stringProp = conf.get("Test", "StringTest", "I am a string!", "I am a comment");
		System.out.println(stringProp.get());
		
		Property<Integer> intProp = conf.get("IntTest", "IntTest", Integer.MAX_VALUE);
		System.out.println(intProp.get());
	}
}
