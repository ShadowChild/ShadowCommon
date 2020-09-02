package uk.co.innoxium.cybernize.zip;


import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipNativeInitializationException;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Zach Piddock on 24/01/2016.
 */
public class ZipUtils {

    /**
     * Unzip it
     *
     * @param zipFile      input zip file
     * @param outputFolder zip file output folder
     */
    public static void unZipIt(String zipFile, String outputFolder) {

        byte[] buffer = new byte[1024];

        try {

            //create output directory is not exists
            File folder = new File(outputFolder);
            if(!folder.exists()) {
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze != null) {

                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separatorChar + fileName);

                System.out.println("file unzip : " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();
                if(ze.isDirectory()) { newFile.mkdirs(); } else newFile.createNewFile();

                if(newFile.isFile()) {

                    FileOutputStream fos = new FileOutputStream(newFile);

                    int len;
                    while((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }

                    fos.close();
                }
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isZip(File file) {

        int fileSignature = 0;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {

            fileSignature = raf.readInt();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return fileSignature == 0x504B0304 || fileSignature == 0x504B0506 || fileSignature == 0x504B0708;
    }

    public static void setUpSevenZip() {

        try {

            SevenZip.initSevenZipFromPlatformJAR();
            System.out.printf("CYBERNIZE: 7zip Version: %s%n", SevenZip.getSevenZipVersion().version);
            System.out.println("CYBERNIZE: 7Zip-JBinding is initialised");
        } catch (SevenZipNativeInitializationException e) {

            e.printStackTrace();
        }
    }
}
