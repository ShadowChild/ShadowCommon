import io.github.shadowchild.cybernize.newconfig.Config;
import io.github.shadowchild.cybernize.newconfig.IniConfig;
import io.github.shadowchild.cybernize.newconfig.JsonConfig;
import io.github.shadowchild.cybernize.util.Resource;

public class NewConfigTest {

	public static void main(String[] args) {
	
		Config conf;
		
		// Test INI Config
		conf = new IniConfig(new Resource("test:INITest.ini"));
		
		
		// Test JSON Config
		conf = new JsonConfig(new Resource("test:JSONConfig.json"));
	}
}
