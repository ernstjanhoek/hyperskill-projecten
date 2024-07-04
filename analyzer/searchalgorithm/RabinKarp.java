package analyzer.searchalgorithm;

public class RabinKarp implements SearchAlgorithm {
    @Override
    public boolean findPattern(byte[] text, byte[] pattern) {
        int tLen = text.length;
        int pLen = pattern.length;

        if (pLen > tLen) {
            return false;
        }

        final int A = 256;  // number of symbols in input alphabet
        final int mod = 101;    // prime number for hashing

        int tHash = 0, pHash = 0;
        int i, j;
        int h = 1;

        // Precompute h = (A^(pLen-1)) % mod
        for (i = 0; i < pLen - 1; i++) {
            h = (h * A) % mod;
        }

        // Calculate initial hash values for the pattern and first window of text
        for (i = 0; i < pLen; i++) {
            pHash = (A * pHash + pattern[i]) % mod;
            tHash = (A * tHash + text[i]) % mod;
        }

        // Slide the pattern over the text
        for (i = 0; i <= tLen - pLen; i++) {
            // Check if the current hash values match
            if (pHash == tHash) {
                // If hashes match, check the actual substrings
                for (j = 0; j < pLen; j++) {
                    if (pattern[j] != text[i + j]) break;
                }
                // If the pattern matches the text substring
                if (j == pLen) return true;
            }

            // Calculate hash for the next window of text
            if (i < tLen - pLen) {
                tHash = (A * (tHash - text[i] * h) + text[i + pLen]) % mod;
                // Ensure tHash is positive
                if (tHash < 0) {
                    tHash = (tHash + mod);
                }
            }
        }
        return false;
    }
}