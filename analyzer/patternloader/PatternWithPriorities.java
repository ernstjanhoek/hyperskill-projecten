package analyzer.patternloader;

public class PatternWithPriorities implements PatternLoader, Comparable<PatternWithPriorities> {
    int priority;
    String pattern;
    String fileType;

    public PatternWithPriorities(int priority, String pattern, String fileType) {
        this.pattern = pattern;
        this.priority = priority;
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    @Override
    public String loadPattern() {
        return pattern;
    }

    @Override
    public byte[] loadPatternAsBytes() {
        return pattern.getBytes();
    }


    @Override
    public int compareTo(PatternWithPriorities otherPattern) {
        int comparison = Integer.compare(priority, otherPattern.priority);
        if (comparison == 0) {
            return pattern.compareTo(otherPattern.pattern);
        } else {
            return comparison;
        }
    }
}
