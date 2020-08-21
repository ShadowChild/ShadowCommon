package me.shadowchild.cybernize.archive.extractor;

import me.shadowchild.cybernize.archive.Archive;

public class RarExtractor extends SevenZipExtractor {

    @Override
    public boolean validate(Archive archive) {

        // TODO: Validate the archive is in fact an old rar-14 format
        return true;
    }
}
