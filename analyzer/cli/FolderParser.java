package analyzer.cli;

public class FolderParser {
    private final String pattern;
    private final String path;
    private final String fileType;

    public FolderParser(String[] args) {
        this.path = args[0];
        this.pattern = args[1];
        this.fileType = args[2];
    }

    public String getPath() {
        return path;
    }

    public String getPattern() {
        return pattern;
    }

    public String getFileType() {
        return fileType;
    }
}
