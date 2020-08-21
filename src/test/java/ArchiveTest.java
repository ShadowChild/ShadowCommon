import me.shadowchild.cybernize.archive.Archive;
import me.shadowchild.cybernize.archive.ArchiveBuilder;
import me.shadowchild.cybernize.zip.ZipUtils;

import java.io.File;
import java.io.IOException;

public class ArchiveTest {

    public static void main(String... args) {

        ZipUtils.setUpSevenZip();

        Archive zip = new ArchiveBuilder(new File(".", "zip.zip")).type(ArchiveBuilder.ArchiveType.ZIP).build();

        Archive seven = new ArchiveBuilder(new File(".", "sevenzip.7z")).type(ArchiveBuilder.ArchiveType.SEVEN_ZIP)
                .outputDirectory(new File(".", "sevenzip")).build();

        Archive rar = new ArchiveBuilder(new File(".", "rar.rar")).type(ArchiveBuilder.ArchiveType.RAR).build();

        Archive rar5 = new ArchiveBuilder(new File(".", "rar5.rar")).type(ArchiveBuilder.ArchiveType.RAR5).build();

        Archive archive = new ArchiveBuilder(new File(".", "run.zip")).type(ArchiveBuilder.ArchiveType.ZIP).build();

        try {

            zip.extract();
            seven.extract();
            rar.extract();
            rar5.extract();
            archive.getAllArchiveItems().forEach(System.out::println);
            zip.getAllArchiveItems().forEach(System.out::println);
            seven.getAllArchiveItems().forEach(System.out::println);
            rar.getAllArchiveItems().forEach(System.out::println);
            rar5.getAllArchiveItems().forEach(System.out::println);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
