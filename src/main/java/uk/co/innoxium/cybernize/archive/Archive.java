package uk.co.innoxium.cybernize.archive;

import uk.co.innoxium.cybernize.archive.extractor.ArchiveExtractor;

import java.io.File;
import java.io.IOException;
import java.util.Set;

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

    public boolean extract() throws IOException {

        if(extractor.validate(this))
            return extractor.extract(this);
        else return false;
    }

    public Set<String> getAllArchiveItems() throws IOException {

        return extractor.getAllArchiveItems(this);
    }
}
