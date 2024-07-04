package analyzer.patternloader;

public class SimplePattern implements PatternLoader {
    String pattern;

    public SimplePattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String loadPattern() {
        return pattern;
    }

    @Override
    public byte[] loadPatternAsBytes() {
        return pattern.getBytes();
    }
}
