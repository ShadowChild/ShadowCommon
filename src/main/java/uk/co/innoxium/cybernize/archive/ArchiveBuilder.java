package uk.co.innoxium.cybernize.archive;

import uk.co.innoxium.cybernize.archive.extractor.*;

import java.io.File;

public class ArchiveBuilder {

    private ArchiveType type;
    private File filePath;
    private File outputDirectory;

    /**
     *
     * @param filePath - The full path to the file
     */
    public ArchiveBuilder(File filePath) {

        this.filePath = filePath;
    }

    /**
     *
     * @param filePath - the path to a local file in the working directory
     */
    public ArchiveBuilder(String filePath) {

        this.filePath = new File(".", filePath);
    }

    /**
     *
     * @param type - The type of the archive to be extracted
     * @return - instance of the ArchiveExtractor
     */
    public ArchiveBuilder type(ArchiveType type) {

        this.type = type;
        return this;
    }

    /**
     *  This output directory will always have the fileName appended
     * @param outputDirectory - A file instance to the output directory
     * @return
     */
    public ArchiveBuilder outputDirectory(File outputDirectory) {

        this.outputDirectory = outputDirectory;
        return this;
    }

    /**
     *  This output directory will always have the fileName appended
     * @param outputDirectory - The path to a local folder in the working directory
     * @return - instance of the ArchiveExtractor
     */
    public ArchiveBuilder outputDirectory(String outputDirectory) {

        this.outputDirectory = new File(".", outputDirectory);
        return this;
    }

    public Archive build() {

        if(type == null) type = getTypeFromArchive();
        if(outputDirectory == null) outputDirectory = new File(".");

        ArchiveExtractor extractor;
        switch(type) {

            case ZIP -> extractor = new ZipExtractor();
            case SEVEN_ZIP -> extractor = new SevenZipExtractor();
            case RAR -> extractor = new RarExtractor();
            case RAR5 -> extractor = new Rar5Extractor();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

        return new Archive(filePath, outputDirectory, type, extractor);
    }

    /**
     *
     * @return will return an ArchiveType if one has not been set already
     */
    private ArchiveType getTypeFromArchive() {

        // TODO: check header of file to determine type, for now we use SevenZip for best compatibility
        return ArchiveType.SEVEN_ZIP;
    }

    public enum ArchiveType {

        ZIP,
        SEVEN_ZIP,
        RAR,
        RAR5 // Rar5 is separate as the format is radically different to the standard Rar format
    }
}
