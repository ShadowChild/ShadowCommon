package uk.co.innoxium.cybernize.archive.extractor;

import uk.co.innoxium.cybernize.archive.Archive;

import java.io.IOException;
import java.util.Set;

public abstract class ArchiveExtractor {

    public abstract boolean validate(Archive archive);

    public abstract boolean extract(Archive archive) throws IOException;

    public abstract Set<String> getAllArchiveItems(Archive archive) throws IOException;
}
