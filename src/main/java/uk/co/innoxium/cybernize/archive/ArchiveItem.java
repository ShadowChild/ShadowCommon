package uk.co.innoxium.cybernize.archive;

public class ArchiveItem {

    private final String archiveName;
    private final String filePath;
    private final boolean isDirectory;

    public ArchiveItem(String archiveName, String filePath, boolean isDirectory) {

        this.archiveName = archiveName;
        this.filePath = filePath;
        this.isDirectory = isDirectory;
    }

    public String getFilePath() {

        return filePath;
    }

    public boolean isDirectory() {

        return isDirectory;
    }

    @Override
    public String toString() {
        return "ArchiveItem{" +
                "'" + archiveName + "'" +
                ", '" + filePath + "'" +
                ", " + (isDirectory ? "true " : "false") +
                '}';
    }
}
