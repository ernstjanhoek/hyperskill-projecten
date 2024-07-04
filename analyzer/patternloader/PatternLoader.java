package analyzer.patternloader;

public interface PatternLoader {
    public String loadPattern();

    byte[] loadPatternAsBytes();
}
