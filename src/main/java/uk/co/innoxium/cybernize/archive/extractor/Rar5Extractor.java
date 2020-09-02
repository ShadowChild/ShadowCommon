package uk.co.innoxium.cybernize.archive.extractor;

import uk.co.innoxium.cybernize.archive.Archive;

public class Rar5Extractor extends SevenZipExtractor {

    @Override
    public boolean validate(Archive archive) {

        // TODO: Validate if the archive is in fact a rar5
        return true;
    }
}
