import uk.co.innoxium.cybernize.archive.Archive;
import uk.co.innoxium.cybernize.archive.ArchiveBuilder;
import uk.co.innoxium.cybernize.zip.ZipUtils;

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

            if(rar.containsFile("dali.jpg")) {

                System.out.println("dali was found");
            }
            if(rar5.containsFile("dali.jpg")) {

                System.out.println("Dali was also found");
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
