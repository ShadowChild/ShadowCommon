import io.github.shadowchild.common.util.Utils;

import java.io.IOException;

/**
 * Created by Zach Piddock on 07/11/2015.
 */
public class DownloadTest {

    public DownloadTest() {

        try {

            Utils.downloadFile("http://build.lwjgl.org/stable/lwjgl.zip");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String... args) {

        new DownloadTest();
    }
}
