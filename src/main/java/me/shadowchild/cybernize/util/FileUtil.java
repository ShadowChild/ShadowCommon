package me.shadowchild.cybernize.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;

public class FileUtil {

    /**
     *
     * Returns a NIO Path based on a string, this allows resource handling that works both inside
     * and outside of your IDE with no modifications
     *
     * usage example: getPath(ClassLoadUtil.getCL().getResource(resourceName), resourceName);
     */
    public static Path getPath(URL url, String resource) throws IOException, URISyntaxException {

        URI uri = url.toURI();
        Path path;
        if(uri.getScheme().equals("jar")) {

            // Internal
            FileSystem fs;
            try {

                fs = FileSystems.newFileSystem(uri, Collections.emptyMap());
            } catch(FileSystemAlreadyExistsException e) {
                
                System.out.println("File system exists already? Fallback on getFileSystem");
                fs = FileSystems.getFileSystem(uri);
                e.printStackTrace();
            }
            path = fs.getPath(resource);
        } else {

            // External
            path = Paths.get(uri);
        }
        return path;
    }

    public static File getFile(String parent, String child, boolean folder) {

        File ret = new File(parent, child);
        if(!ret.exists()) {

            ret.getParentFile().mkdirs();
            if(folder) ret.mkdir();
            else {

                try {

                    ret.createNewFile();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    public static File getFile(File file, boolean folder) {

        if(!file.exists()) {

            file.getParentFile().mkdirs();
            if(folder) file.mkdir();
            else {

                try {

                    file.createNewFile();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
