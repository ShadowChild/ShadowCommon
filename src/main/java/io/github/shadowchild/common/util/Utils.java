package io.github.shadowchild.common.util;


import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipNativeInitializationException;
import org.apache.commons.math3.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Observer;

/**
 * Created by Zach Piddock on 07/11/2015.
 */
public class Utils {

    private static final String[] SI_UNITS = { "B", "kB", "MB", "GB", "TB", "PB", "EB" };
    private static final String[] BINARY_UNITS = { "B", "KiB", "MiB", "GiB", "TiB", "PiB", "EiB" };

    /**
     * The fully customized way to the the downloader
     *
     * @param url          - The URL to download from
     * @param observer     - The observer to handle progress updates
     * @param outputFolder - The folder to save the file to
     * @throws IOException
     */
    public static void downloadFile(String url, Observer observer, File outputFolder) throws
            IOException {

        Download download = new Download(new URL(url), observer, outputFolder);
        download.download();
    }

    /**
     * The most useful way to use the downloader
     * Uses the default observer
     *
     * @param url          - The URL to download from
     * @param outputFolder - The folder to save the file to
     * @throws IOException
     */
    public static void downloadFile(String url, File outputFolder) throws IOException {

        Download download = new Download(new URL(url), outputFolder);
        download.download();
    }

    /**
     * The least useful way to use the downloader
     * The is usually used for testing as it saves to a test directory
     *
     * @param url - The URL to download from
     * @throws IOException
     */
    public static void downloadFile(String url) throws IOException {

        Download download = new Download(new URL(url));
        download.download();
    }

    /**
     * Gets the file name from a supplied URL
     *
     * @param url - The URL to get the file name from
     * @return - The String of the filename from the URL
     */
    public static String getFileName(URL url) {

        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    public static Pair<Float, String> humanReadableByteCount(long bytes, boolean useSIUnits, Locale locale) {

        final String[] units = useSIUnits ? SI_UNITS : BINARY_UNITS;
        final int base = useSIUnits ? 1000 : 1024;

        // When using the smallest unit no decimal point is needed, because it's the exact number.
        if(bytes < base) {
            return new Pair<>((float)bytes, units[0]);
        }

        final int exponent = (int)(Math.log(bytes) / Math.log(base));
        final String unit = units[exponent];
        float size = Float.parseFloat(
                String.format(locale, "%.1f", bytes / Math.pow(base, exponent)));
        return new Pair<>(size, unit);
    }

    public static Pair<Float, String> humanReadableByteCount(long bytes, boolean useSIUnits) {

        return humanReadableByteCount(bytes, useSIUnits, Locale.ENGLISH);
    }

    public static Pair<Float, String> humanReadableByteCount(long bytes) {

        return humanReadableByteCount(bytes, true);
    }

    public static void initialise() {

        try {

            SevenZip.initSevenZipFromPlatformJAR();
        } catch(SevenZipNativeInitializationException e) {

            e.printStackTrace();
        }
    }
}
