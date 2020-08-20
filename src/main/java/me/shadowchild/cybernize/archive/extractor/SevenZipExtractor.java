package me.shadowchild.cybernize.archive.extractor;

import me.shadowchild.cybernize.archive.Archive;

public class SevenZipExtractor extends ArchiveExtractor {

    @Override
    public boolean validate(Archive archive) {

        return false;
    }

    @Override
    public boolean extract(Archive archive) {

        return false;
    }
}
