import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileConfig;
import me.shadowchild.cybernize.setting.Config;
import me.shadowchild.cybernize.setting.Setting;
import me.shadowchild.cybernize.setting.SettingsHandler;
import me.shadowchild.cybernize.setting.SettingsHolder;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@SettingsHolder(id = "test")
public class SettingTest {

    @Setting(category = "core")
    @Setting.Comment("This is a comment")
    public static int x = 9;

    @Setting(category = "core")
    public static int y = 1;

    @Setting(category = "core")
    public static String test = "";

    static {

        // Initialise the settings handler as soon as possible and load config asap
        SettingsHandler.addHolder(SettingTest.class);
        try {

            SettingsHandler.load(); // This can be called whenever you wish to load the config
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }
    }

    // MUST BE STATIC
    @Config
    public static CommentedFileConfig getConfig() {

        return CommentedFileConfig
                .builder(new File("./config/", "test.toml"))
                .autosave()
                .autoreload()
                .charset(StandardCharsets.UTF_8)
                .onFileNotFound((file, cfgFormat) -> {

                    Files.createDirectories(file.getParent());
                    Files.createFile(file);
                    cfgFormat.initEmptyFile(file);
                    return false;
                })
                .build();
    }

    public static void main(String... args) {

        // Settings Setup

        System.out.println(x + " " + y);
        System.out.println(test);

        x = 87;

        test = "Not a test";
    }
}
