package analyzer.searchalgorithm;

public class BasicSearchAlgorithm implements SearchAlgorithm {
    public BasicSearchAlgorithm() {}

    @Override
    public boolean findPattern(byte[] byteSequence, byte[] pattern) {
        for (int i = 0; i <= byteSequence.length - pattern.length; i++) {
            boolean found = true;
            for (int j = 0; j < pattern.length; j++) {
                if (byteSequence[i + j] != pattern[j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }
        return false;
    }
}
