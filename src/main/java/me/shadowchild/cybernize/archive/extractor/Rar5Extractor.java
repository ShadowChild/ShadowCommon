package me.shadowchild.cybernize.archive.extractor;

import me.shadowchild.cybernize.archive.Archive;

public class Rar5Extractor extends SevenZipExtractor {

    @Override
    public boolean validate(Archive archive) {

        // TODO: Validate if the archive is in fact a rar5
        return true;
    }
}
