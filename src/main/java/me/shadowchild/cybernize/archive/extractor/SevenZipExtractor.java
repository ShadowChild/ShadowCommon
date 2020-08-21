package me.shadowchild.cybernize.archive.extractor;

import me.shadowchild.cybernize.archive.Archive;
import net.sf.sevenzipjbinding.*;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SevenZipExtractor extends ArchiveExtractor {

    @Override
    public boolean validate(Archive archive) {

        return true;
    }

    @Override
    public boolean extract(Archive archive) {

        RandomAccessFile randomAccessFile = null;
        IInArchive inArchive = null;

        boolean successful = false;

        try {

            randomAccessFile = new RandomAccessFile(archive.archive, "r");
            inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile));

            System.out.printf("CYBERNIZE: There are %d items in the archive %s%n", inArchive.getNumberOfItems(), archive.archive.getName());

            ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();

            for(ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {

                final int[] hash = new int[] { 0 };

                if(!item.isFolder()) {

                    ExtractOperationResult result;

                    final long[] sizeArray = new long[1];
                    result = item.extractSlow(data -> {

                        // write the data to file
                        try {

                            writeToFile(item.getPath(), archive.outputDirectory.getCanonicalPath(), data);
                        } catch (IOException e) {

                            e.printStackTrace();
                        }

                        hash[0] ^= Arrays.hashCode(data);
                        sizeArray[0] += data.length;
                        return data.length;
                    });

                    if(result == ExtractOperationResult.OK) {

                        System.out.printf("CYBERNIZE: Extracted file %s%n", item.getPath());
                    } else {

                        System.err.println("CYBERNIZE: Error extracting item: " + result);
                    }
                }
            }
//            inArchive.extract(null, false, new ExtractCallback(inArchive));
            successful = true;
        } catch(Exception e) {

            System.out.println("CYBERNIZE: Rar5 Extraction error!");
            e.printStackTrace();
        } finally {

            if(inArchive != null) {

                try {

                    inArchive.close();
                } catch (SevenZipException e) {

                    e.printStackTrace();
                }
            }

            if(randomAccessFile != null) {

                try {

                    randomAccessFile.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

        return successful;
    }

    @Override
    public Set<String> getAllArchiveItems(Archive archive) throws IOException {

        Set<String> set = new HashSet<>();

        RandomAccessFile randomAccessFile = null;
        IInArchive inArchive = null;

        randomAccessFile = new RandomAccessFile(archive.archive, "r");
        inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile));

        System.out.printf("CYBERNIZE: There are %d items in the archive %s%n", inArchive.getNumberOfItems(), archive.archive.getName());

        ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();

        Arrays.stream(simpleInArchive.getArchiveItems()).iterator().forEachRemaining(item -> {

            try {

                set.add(item.getPath());
            } catch (SevenZipException e) {

                e.printStackTrace();
            }
        });

        randomAccessFile.close();
        inArchive.close();

        return set;
    }

    private void writeToFile(String fileName, String parentFile, byte[] data) throws IOException {

        FileOutputStream fos;
        File file = new File(parentFile, fileName);
        file.getParentFile().mkdirs();
        fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
    }
}
