import uk.co.innoxium.cybernize.net.Hastebin;

import java.io.IOException;

public class HastebinTest {

    static final String data = "This is a test\n With a newline";

    public static void main(String... args) {

        try {

            String rawRet = Hastebin.post(data, true);
            String ret = Hastebin.post(data, false);
            System.out.println(rawRet);
            System.out.println(ret);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
