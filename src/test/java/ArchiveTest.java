import me.shadowchild.cybernize.archive.Archive;
import me.shadowchild.cybernize.archive.ArchiveBuilder;

import java.io.File;

public class ArchiveTest {

    public static void main(String... args) {

        Archive zip = new ArchiveBuilder(new File(".", "zip.zip")).type(ArchiveBuilder.ArchiveType.ZIP).build();
        Archive seven = new ArchiveBuilder(new File(".", "seven.7z")).outputDirectory(new File(".", "/seven")).build();
        Archive rar = new ArchiveBuilder(new File(".", "rar.rar")).type(ArchiveBuilder.ArchiveType.RAR).build();
        Archive rar5 = new ArchiveBuilder(new File(".", "rar5.rar")).type(ArchiveBuilder.ArchiveType.RAR5).build();

        zip.extractor.extract(zip);
    }
}
