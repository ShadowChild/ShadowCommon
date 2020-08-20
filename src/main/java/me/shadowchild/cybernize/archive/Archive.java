package me.shadowchild.cybernize.archive;

import me.shadowchild.cybernize.archive.extractor.ArchiveExtractor;

import java.io.File;

public class Archive {

    // TODO: Update to record

    public final File archive;
    public final File outputDirectory;
    public final ArchiveBuilder.ArchiveType type;
    public final ArchiveExtractor extractor;

    public Archive(File archive, File outputDirectory, ArchiveBuilder.ArchiveType type, ArchiveExtractor extractor) {

        this.archive = archive;
        this.outputDirectory = outputDirectory;
        this.type = type;
        this.extractor = extractor;
    }
}
