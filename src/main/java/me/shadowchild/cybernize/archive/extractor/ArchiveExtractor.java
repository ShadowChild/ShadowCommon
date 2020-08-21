package me.shadowchild.cybernize.archive.extractor;

import me.shadowchild.cybernize.archive.Archive;

import java.io.IOException;

public abstract class ArchiveExtractor {

    public abstract boolean validate(Archive archive);

    public abstract boolean extract(Archive archive) throws IOException;
}
