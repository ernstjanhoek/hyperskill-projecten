package analyzer.searchalgorithm;

public class KnuthMorrisPratt implements SearchAlgorithm {
    private static int[] buildPartialMatchTable(byte[] patternArray) {
        if (patternArray == null || patternArray.length == 0) {
            return new int[0];
        }
        int[] matchTable = new int[patternArray.length];
        matchTable[0] = 0;
        int previousPointer = 0;
        for (int i = 1; i < patternArray.length; i++) {
            if (patternArray[i] == patternArray[previousPointer]) {
                matchTable[i] = previousPointer + 1;
                previousPointer++;
            } else {
                previousPointer = 0;
            }
        }
        return matchTable;
    }

    @Override
    public boolean findPattern(byte[] data, byte[] pattern) {
        int[] matchTable = buildPartialMatchTable(pattern);

        for (int i = 0; i <= data.length - pattern.length; i++) {
            byte[] subArray = subArray(data, i, pattern.length);

            for (int k = 0; k < subArray.length; k++) {
                if (subArray[k] != pattern[k]) {
                    // shift and break
                    i = i + matchTable[k];
                    break;
                } else if (k == subArray.length - 1 && subArray[k] == pattern[k]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static byte[] subArray(byte[] data, int startIndex, int length) {
        byte[] subArray = new byte[length];
        for (int i = 0; i < length; i++) {
            subArray[i] = data[startIndex + i];
        }
        return subArray;
    }

}
