import uk.co.innoxium.cybernize.util.MathUtils;
import uk.co.innoxium.cybernize.util.Utils;
import org.apache.commons.math3.util.Pair;

import java.io.IOException;

/**
 * Created by Zach Piddock on 07/11/2015.
 */
public class DownloadTest {

    public DownloadTest() {

        try {

            Utils.downloadFile("http://build.lwjgl.org/stable/lwjgl.zip");
            Pair<Float, String> size = MathUtils.humanReadableByteCount(45645645456L);
            System.out.println(
                    String.format("Size = %s, Type = %s", size.getFirst(), size.getSecond()));
        } catch(IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String... args) {

        new DownloadTest();
    }
}
