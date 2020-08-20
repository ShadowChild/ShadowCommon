package me.shadowchild.cybernize.archive.extractor;

import me.shadowchild.cybernize.archive.Archive;
import me.shadowchild.cybernize.archive.ArchiveBuilder;

import java.io.File;

public abstract class ArchiveExtractor {

    public abstract boolean validate(Archive archive);

    public abstract boolean extract(Archive archive);
}
