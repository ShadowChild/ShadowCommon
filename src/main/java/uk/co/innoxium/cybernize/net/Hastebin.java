package uk.co.innoxium.cybernize.net;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @Deprecated Hastebin has been mangled and no longer exists, looking in to an alternative
 *
 * Modified to work with hasteb.in rather than hastebin.com
 *
 * @Author Kaimu-kun at https://github.com/kaimu-kun/hastebin.java
 */
@Deprecated(since = "1.1.7")
public class Hastebin {

    public static String post(String text, boolean raw) throws IOException {

        byte[] postData = text.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        String requestURL = "https://hasteb.in/documents";
        URL url = new URL(requestURL);
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "Cybernize Hastebin Java Api");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);

        String response = null;
        DataOutputStream wr;
        try {

            wr = new DataOutputStream(conn.getOutputStream());
            wr.write(postData);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            response = reader.readLine();
        } catch(IOException e) {

            e.printStackTrace();
        }

        if(response != null && response.contains("\"key\"")) {

            response = response.substring(response.indexOf(":") + 2, response.length() - 2);

            String postURL = raw ? "https://hasteb.in/raw/" : "https://hasteb.in/";
            response = postURL + response;
        }
        return response;
    }
}