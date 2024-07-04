package analyzer.cli;

public class BasicParser {
    private final AlgorithmEnum algorithm;
    private final String path;
    private final String pattern;
    private final String fileType;

    public BasicParser(String[] args) {
        AlgorithmEnum option = switch(args[0]) {
            case "--KMP" -> AlgorithmEnum.KMP;
            case "--naive" -> AlgorithmEnum.NAIVE;
            default -> AlgorithmEnum.NAIVE;
        };
        this.algorithm = option;
        this.path = args[1];
        this.pattern = args[2];
        this.fileType = args[3];
    }

    public AlgorithmEnum getAlgorithm() {
        return algorithm;
    }

    public String getFileType() {
        return fileType;
    }

    public String getPattern() {
        return pattern;
    }

    public String getPath() {
        return path;
    }

    public enum AlgorithmEnum {
        NAIVE, KMP;
    }
}
